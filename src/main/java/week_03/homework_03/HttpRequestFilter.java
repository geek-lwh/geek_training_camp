package week_03.homework_03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
