package com.lifecycle.autotest.util;


import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jin.WeiMin
 * create by 2018-07-13 20:39
 * 其实最好的使用方式应该是拿着session一段时间，但是由于网络的不确定性。实践出的最好方式是用完这个session后立刻关闭这个session。但带来了更多的成本，索性这样的成本还能接受 by 2019年2月12日
 **/
public class LockSftpUtil implements SftpInterface {
    private Logger logger = LoggerFactory.getLogger(LockSftpUtil.class);
    private static volatile Map<Session, Long> sumConnections = Collections.synchronizedMap(new HashMap<Session, Long>());

    /**
     * 计数器,不允许超过最大限制的数量的session出现
     */
    private volatile Map<String, AtomicInteger> lockNumMap = new ConcurrentHashMap<>();

    /**
     * 锁定，同一个sftp，同一刻，只有一个任务可以创建session
     */
    private volatile Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();
    private int maxConnectionNum = 5;
    //超时数,10分钟
    private int timeout = 1000 * 60 * 10;
    private String username;
    private String password;
    private String ip;
    private int port;
    private Properties config;
    private JSch jsch = new JSch();

    public LockSftpUtil(String username, String password, String ip, int port, Properties config) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.config = config;
        lockNumMap.computeIfAbsent(ip, e -> new AtomicInteger());
        lockMap.computeIfAbsent(ip, e -> new ReentrantLock());
    }

    /**
     * 默认关闭 StrictHostKeyChecking
     *
     * @param username 远程要连接的服务器的用户名
     * @param password 远程要连接的服务器的密码
     * @param ip       远程服务器ip
     * @param port     远程服务器的ssh服务端口
     * @throws JSchException
     */
    private Session createSession(String username, String password, String ip, int port) throws Exception {

        /**
         * 先对创建锁 进行锁定，锁定期间进行等待。否则会造成底下的无限循环
         */
        if (lockMap.get(ip).tryLock()) {
            //如果连接数满了。无限等待。直到有可用的连接
            while (checkExistMoreThanMaxConnectionNums(lockNumMap.get(ip))) {
                Thread.sleep(2 * 1000);
            }
            try {
                this.username = username;
                this.password = password;
                this.ip = ip;
                this.port = port;
                // 根据用户名，主机ip，端口获取一个Session对象
                Session session = jsch.getSession(username, ip, port);
                if (password != null) {
                    // 设置密码
                    session.setPassword(password);
                }
                config.put("StrictHostKeyChecking", "no");
                config.put("ClearAllForwardings", "yes");
                // 为Session对象设置properties
                session.setConfig(config);
                session.setServerAliveInterval(10000);
                session.setServerAliveCountMax(1200);
                int maxRetryNumber = 3;
                for (int i = 0; i < maxRetryNumber; i++) {
                    try {
                        // 通过Session建立链接
                        session.connect();
                        lockNumMap.get(ip).getAndIncrement();
                        logger.info("connected Session:" + i + " times successfully");
                        break;
                    } catch (Exception e) {
                        logger.info("session connect " + i + " times, error info:" + e.getClass().getName() + "," + e.getMessage() + "," + Arrays.toString(e.getStackTrace()));
                        if (i == (maxRetryNumber - 1)) {
                            throw e;
                        }
                    }
                }
                return session;
            } finally {
                lockMap.get(ip).unlock();
            }
        } else {
            throw new RuntimeException("无法获得锁");
        }


    }

    public void checkConnection() {
        synchronized (LockSftpUtil.class) {
            Iterator<Map.Entry<Session, Long>> iterable = sumConnections.entrySet().iterator();
            while (iterable.hasNext()) {
                Map.Entry<Session, Long> sessionLongEntry = iterable.next();
                Session session = sessionLongEntry.getKey();
                Long startTime = sessionLongEntry.getValue();
                if (session == null || !session.isConnected()) {
                    iterable.remove();
                }
            }
        }

    }

    /**
     * 检查是否超过了最大连接数
     * @param atomicInteger
     * @return true 是超过了。 false 没超过
     */
    private boolean checkExistMoreThanMaxConnectionNums(AtomicInteger atomicInteger) {
        Assert.isTrue(atomicInteger != null, "参数为null");
        return atomicInteger.get() >= maxConnectionNum;
    }


    @Override
    public ChannelSftp createSFTPChannel() throws Exception {
        Session session = createSession(username, password, ip, port);
        return createSFTPChannel(session);

    }

    @Override
    public ChannelSftp createSFTPChannel(Session session) throws Exception {
        Assert.isTrue(session != null, "session 为空");
        // 打开SFTP通道
        Channel channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        logger.info("Connected channel successfully ");
        return (ChannelSftp) channel;

    }
//    /**
//     * 为每一个流建立一个channel，而不是复用一个，否则在并发情况下会出问题
//     *
//     * @return
//     * @throws JSchException
//     */
//    public ChannelSftp getChannel() throws Exception {
//        while (true) {
//            if (sumConnections.size() == 5) {
//                Thread.sleep(2 * 1000);
//                checkConnection();
//            } else {
//                synchronized (LockSftpUtil.class) {
//                    if (sumConnections.size() < 5) {
//                        return createSFTPChannel();
//                    }
//                }
//            }
//        }
//    }

    /**
     * @param channel
     */
    @Override
    public void closeChannel(Channel channel) {
        closeChannel(channel, true);
    }

    @Override
    public void closeChannel(Channel channel, boolean closeSession) {
        if (channel == null) {
            return;
        }

        if (!channel.isClosed()) {
            channel.disconnect();
        }
        Session session = null;
        try {
            session = channel.getSession();
        } catch (Exception e) {

        }

        if (session == null) {
            return;
        }

        if (closeSession) {
            if (session.isConnected()) {
                session.disconnect();
                lockNumMap.get(ip).getAndDecrement();
            } else {
                lockNumMap.get(ip).getAndDecrement();
            }
        }

    }

    @Override
    public void closeSession(Session session) {
        if (session == null) {
            return;
        }

        if (session.isConnected()) {
            session.disconnect();
            lockNumMap.get(ip).getAndDecrement();
        } else {
            lockNumMap.get(ip).getAndDecrement();
        }

    }


    /**
     * 获取sftp
     * 直接传入的参数
     *
     * @param properties 配置参数
     * @param username   参数值
     * @param password   同上
     * @param domain     同上
     * @param port       同上
     * @return
     */
    public synchronized static LockSftpUtil getSftpUtils(Properties properties, String username, String password, String domain, Integer port) {
        LockSftpUtil sftpUtils = new LockSftpUtil(username, password, domain, port, properties);
        return sftpUtils;
    }


    /**
     * 创建一个文件目录
     */
    public static void createDir(String createPath, ChannelSftp sftp) {
        try {
            sftp.cd("/");//回到初始目录
            if (isDirExist(createPath, sftp)) {
            } else {
                String pathArray[] = createPath.split("/");
                for (String path : pathArray) {
                    if (path.equals("")) {
                        continue;
                    }
                    if (isDirExist(path, sftp)) {
                        sftp.cd(path);
                        if (isDirExist(createPath, sftp)) {
                        }
                    } else {
                        // 建立目录
                        sftp.mkdir(path);
                        // 进入并设置为当前目录
                        sftp.cd(path);
                    }
                }
            }
        } catch (SftpException e) {
            throw new RuntimeException("create path fail:" + e.getMessage());
        }

    }


    /**
     * 判断目录是否存在
     */
    public static boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 判断远程文件是否存在
     *
     * @param srcSftpFilePath
     * @return
     * @throws SftpException
     */
    public static boolean isFileExist(String srcSftpFilePath, ChannelSftp channelSftp) throws SftpException {
        boolean isExitFlag = false;
        // 文件大于等于0则存在文件
        if (getFileSize(srcSftpFilePath, channelSftp) >= 0) {
            isExitFlag = true;
        }
        return isExitFlag;
    }

    /**
     * 得到远程文件大小
     *
     * @param srcSftpFilePath
     * @return 返回文件大小，如返回-2 文件不存在，-1文件读取异常
     * @throws SftpException
     */
    public static long getFileSize(String srcSftpFilePath, ChannelSftp channelSftp) throws SftpException {
        long fileSize = 0;//文件大于等于0则存在
        try {
            SftpATTRS sftpATTRS = channelSftp.lstat(srcSftpFilePath);
            fileSize = sftpATTRS.getSize();
        } catch (Exception e) {
            fileSize = -1;//获取文件大小异常
            if (e.getMessage().toLowerCase().equals("no such file")) {
                fileSize = -2;//文件不存在
            }
        }
        return fileSize;
    }

    @Override
    public Session createSession() throws Exception {
        return createSession(username, password, ip, port);
    }

    /**
     * 替换后缀    xxx.csv  -> xxx.zip     xxx-> xxx.zip
     *
     * @param name
     * @param suffix
     * @return
     */
    public static String replaceSuffix(String name, String suffix) {
        String newName = null;
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf != -1) {//有后缀 去除后缀 拼接
            newName = name.substring(0, lastIndexOf + 1) + suffix;
        } else { //没有后缀 直接加.
            newName = name + "." + suffix;
        }
        return newName;
    }
}
