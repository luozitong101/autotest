package com.lifecycle.autotest.util;

/**
 * @program: adiFox
 * @description: ${description}
 * @author: weecj
 * @create: 2018-12-17 17:12
 **/
public class StringUtil {

    public static final char[] character = {'0', '1','2','3','4','5','6','7','8','9','0',
    'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 返回随机数
     * @param length：随机数长度
     * @param data:取值范围
     * @return
     */
    public static String getRand(int length, char[] data) {
        StringBuffer result = new StringBuffer();
        for(int i=0; i<length; ++i) {
            int rand = (int) (Math.random()*data.length);
            result.append(data[rand]);
        }
        return result.toString();
    }

    public static void main(String[]args) {
        System.out.println(isEmpty(null));
    }

}