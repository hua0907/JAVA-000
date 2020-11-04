package io.github.kimmking.gateway.filter;

import io.github.kimmking.gateway.constant.HeaderConstant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class MyHttpRequestFilterImpl implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set(HeaderConstant.NOI_HEADER, "huadeyi");
    }
}
