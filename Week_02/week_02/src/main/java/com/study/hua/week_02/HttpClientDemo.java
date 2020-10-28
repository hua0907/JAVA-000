package com.study.hua.week_02;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClientDemo implements ApplicationRunner {

    private static final String URL = "http://localhost:8081/v1/api/hello";

    private static final int SUCCESS_CODE = 200;

    private static final String Charset = "UTF-8";

    @Override
    public void run(ApplicationArguments args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            if (null != statusLine && SUCCESS_CODE == statusLine.getStatusCode()) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, Charset);
                System.out.println("返回结果：" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
