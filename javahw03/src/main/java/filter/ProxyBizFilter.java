package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class ProxyBizFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        if(uri.startsWith("/nioServer")){
            System.out.println("get request for "+uri+", context is "+ctx);
        }else{
            throw new RuntimeException("wrong uri prefix");
        }
        HttpHeaders headers = fullRequest.headers();
        if(headers == null){
            throw new RuntimeException("wrong http request");
        }
        headers.add("tag", this.getClass().getSimpleName());
    }
}
