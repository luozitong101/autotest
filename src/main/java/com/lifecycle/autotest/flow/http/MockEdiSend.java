package com.lifecycle.autotest.flow.http;

import com.lifecycle.autotest.model.request.*;
import com.lifecycle.autotest.model.response.EdiReallySendResponse;
import com.lifecycle.autotest.model.response.EdiSendQueryResponse;
import com.lifecycle.autotest.model.response.EdiSendVo;
import com.lifecycle.autotest.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 发sta range,article,article list,pos&sta到sftp
 * </p>
 * url :http://192.168.132.246:24789/servlets/binserv/Rest
 *
 * @author luoyong
 * @date 2019-11-27 下午 2:21
 */

@Slf4j
public class MockEdiSend {

    @Autowired
    RestTemplate restTemplate;

    String restUrl = "http://192.168.132.246:24789/servlets/binserv/Rest";

    String apurl = "http://192.168.132.246:24789/servlets/binserv/AP ";

    public Transactions mockQueryParam(long tmid, String account, String salesChannel, String interfaceName) {
        Transactions transactions = new Transactions();
        transactions.setCommand("Query");
        transactions.setId(1);
        Params params = new Params();
        List<String> columns = new ArrayList<>();
        columns.add("B_FAIR_ID");
        columns.add("ID");
        columns.add("ACCOUNT");
        columns.add("SALES_CHANNEL");
        columns.add("INTERFACE_NAME");
        params.setColumns(columns);
        params.setCount(true);
        params.setRange(20);
        params.setStart(0);
        params.setTable("V_FAIR_SEND");
        Expr1 expr1 = new Expr1();
        expr1.setCombine("and");
        Expr1 expr1_1 = new Expr1();
        expr1_1.setColumn("B_FAIR_ID");
        expr1_1.setCondition(tmid);
        expr1.setExpr1(expr1_1);

        Expr2 expr2_2 = new Expr2();
        expr2_2.setColumn("ACCOUNT");
        expr2_2.setCondition(account);
        expr1.setExpr2(expr2_2);
        params.setExpr1(expr1);

        Expr2 expr2 = new Expr2();
        expr2.setColumn("SALES_CHANNEL");
        expr2.setCondition(salesChannel);
        params.setExpr2(expr2);
        transactions.setParams(params);

        return transactions;
    }

    /**
     * 将需要发送的edi 数据 查询出来
     **/
    public List<EdiSendVo> mockQueryEdiSend(long tmid, String account, String salesChannel, String interfaceName) throws Exception {
        Transactions transactions = mockQueryParam(tmid, account, salesChannel, interfaceName);
        String param = JsonUtil.objectToString(transactions);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("transactions", param);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(restUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            List<EdiSendVo> ediSendVos = new ArrayList<>();
            log.info(" edi query success,queryParam:{}", param);
            String body = response.getBody();
            EdiSendQueryResponse ediSendQueryResponse = JsonUtil.stringToObject(body, EdiSendQueryResponse.class);
            if (ediSendQueryResponse.getCode() == 0) {

                List<List<Object>> rows = ediSendQueryResponse.getRows();
                for (List<Object> row : rows) {
                    if (row != null && row.size() > 0) {
                        EdiSendVo ediSendVo = new EdiSendVo();
                        ediSendVo.setId((Integer) row.get(1));
                        ediSendVo.setTmid((Long) row.get(0));
                        ediSendVo.setAccount((String) row.get(2));
                        ediSendVo.setChannel((String) row.get(3));
                        ediSendVo.setInterfaceName((String) row.get(4));
                        ediSendVos.add(ediSendVo);

                    }
                }
            }
            return ediSendVos;
        }

        return null;
    }


    /**
     * 将edi 通过接口发送出去
     **/
    public void doSendData(List<EdiSendVo> vos) throws Exception {

        // first request url : http://192.168.132.246:24789/servlets/binserv/AP type:post
        SendRequestData4Log sendRequestData4Log = new SendRequestData4Log();
        sendRequestData4Log.setCommand("com.agilecontrol.portal7.AdminPanelCmd");
        sendRequestData4Log.setId(1);
        SendRequestData4Log.Params params = new SendRequestData4Log().new Params();
        params.setCmd("portal7.recordLog");
        params.setDesc("Send Data");
        params.setId(690);
        sendRequestData4Log.setParams(params);

        String requestLogParam = JsonUtil.objectToString(sendRequestData4Log);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("transactions", requestLogParam);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(restUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // log request success
            log.info("request for record log success,param:{}", requestLogParam);
        }


        // second request for touch time task
        ReallySendEdiData reallySendEdiData = new ReallySendEdiData();
        reallySendEdiData.setCallbackEvent("ExecuteWebAction");
        reallySendEdiData.setCmd("com.lifecycle.adifoxInterface.cmd.SendBasicData");
        reallySendEdiData.setCommand("com.lifecycle.adifoxInterface.cmd.AdifoxInterfaceCmd");
        ReallySendEdiData.Query query = new ReallySendEdiData().new Query();
        ReallySendEdiData.Query.Query2 query2 = query.new Query2();

        query2.setDir_perm(1);
        query2.setInit_query(false);
        query2.setFixedcolumns("");
        query2.setParam_str2("");
        query2.setQlcid(2720639);
        query2.setTableId(17656);
        query.setTable(17656);
        List<String> selects = Objects.requireNonNull(vos).parallelStream().map(item -> item.getId() + "").collect(Collectors.toList());
        query.setSelection(selects);
        reallySendEdiData.setQuery(query);

        String sendDataRequest = JsonUtil.objectToString(reallySendEdiData);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(sendDataRequest, headers);
        EdiReallySendResponse reallySendResponse = restTemplate.postForEntity(restUrl, formEntity, EdiReallySendResponse.class).getBody();
        if (reallySendResponse != null && reallySendResponse.getCode() == 0) {
            log.info("edi send data touch time task success,param:{}", sendDataRequest);
        } else {
            log.error("edi send data touch time task failure,param:{}", sendDataRequest);
        }

    }


    /**
     * 查询edi send log 表, status 自动为success 即为成功
     **/
    public void cycle(List<EdiSendVo> vos ) {

        // 组装请求入参


    }
}
