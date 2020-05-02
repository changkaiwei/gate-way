package com.demo.gateway.route;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author V
 */
@Component
/**继承AbstractRoutePredicateFactory并填充泛型aabbccConfig*/
public class  TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {
    public TimeBetweenRoutePredicateFactory(){
        super(TimeBetweenConfig.class);
    }

    /**主要业务逻辑*/
    @Override
    public Predicate<ServerWebExchange> apply( TimeBetweenConfig config ){
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return exchange ->{
            LocalTime now = LocalTime.now();
            return now.isAfter(start)&&now.isBefore(end);
        };
    }

    /**映射aabbccConfig与YML*/
    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList("start","end");
    }
}
