package com.demo.gateway.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author V
 */
@Component
@Slf4j
public class PreLogGatewayFilterFactory  extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply( NameValueConfig config ){
        return (( exchange, chain ) -> {
            log.info("请求参数...{},{}",config.getName(),config.getValue());
            //修改后的请求（此处仅演示 未修改）
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .build();
            //修改后的exchange（此处仅演示 未修改）
            ServerWebExchange build = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
            //这次过滤器执行完成，交给加下来的过滤器
            return chain.filter(build);
        });
    }
}
