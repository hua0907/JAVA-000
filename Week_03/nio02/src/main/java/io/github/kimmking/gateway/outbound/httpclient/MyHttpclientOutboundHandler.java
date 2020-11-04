package io.github.kimmking.gateway.outbound.httpclient;

import io.github.kimmking.gateway.constant.HeaderConstant;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class MyHttpclientOutboundHandler {

    private String backendUrl;

    public MyHttpclientOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        String url = this.backendUrl + fullRequest.uri();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);
        String nioValue = fullRequest.headers().get(HeaderConstant.NOI_HEADER);
        System.out.println("从header 中取到 nio 对应的值是：" + nioValue);
        httpGet.setHeader(HeaderConstant.NOI_HEADER, nioValue);
        try (CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet)) {

            byte[] body = EntityUtils.toByteArray(closeableHttpResponse.getEntity());
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(closeableHttpResponse.getFirstHeader("Content-Length").getValue()));
            ctx.write(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
