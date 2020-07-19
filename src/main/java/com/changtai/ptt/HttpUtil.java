package com.changtai.ptt;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import java.util.List;

public class HttpUtil {

    /**
     * get 请求
     * @param url
     * @return http response code
     * @throws Exception
     */
    public static int doGet(String url) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            //HttpEntity entity = response.getEntity();
            //EntityUtils.consume(entity);
            return response.getCode();
        }
    }

    /**
     * post 请求
     * @param url
     * @param paramList
     * @return http response code
     * @throws Exception
     */
    public static int doPost(String url, List<NameValuePair> paramList) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(paramList));

        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            //HttpEntity entity = response.getEntity();
            //EntityUtils.consume(entity);
            return response.getCode();
        }
    }
}
