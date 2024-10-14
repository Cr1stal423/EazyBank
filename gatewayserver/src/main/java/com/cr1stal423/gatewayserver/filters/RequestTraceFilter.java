package com.cr1stal423.gatewayserver.filters;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Component
@Order(1)
@Slf4j
@AllArgsConstructor
public class RequestTraceFilter implements GlobalFilter {
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)){
            log.debug("eazyBank-correlation-id found in RequestTraceFilter : {}",
                    filterUtility.getCorrelationId(requestHeaders));
        } else {
            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange,correlationId);
            log.debug("eazyBank-correlation-id generated in RequestTraceFilter : {}",correlationId);
        }
        return chain.filter(exchange);
    }

    public boolean isCorrelationIdPresent(HttpHeaders requestHeaders){
        if (filterUtility.getCorrelationId(requestHeaders) != null){
            return true;
        } else {
            return false;
        }
    }
    public String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
