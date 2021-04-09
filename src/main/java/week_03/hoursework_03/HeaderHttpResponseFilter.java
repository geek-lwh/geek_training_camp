package week_03.hoursework_03;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("abc", "aaa");
        response.headers().set("abcd", "bbb");
    }
}
