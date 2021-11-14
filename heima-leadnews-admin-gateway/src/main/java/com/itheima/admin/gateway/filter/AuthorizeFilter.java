package com.itheima.admin.gateway.filter;

import com.itheima.admin.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/2 20:42
 * @Version 1.0
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1、获取request、response
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 2、判断是否是登录操作  是：放行  不是：获取token并校验
        String path = request.getURI().getPath();
        if (path.startsWith("/admin/login/in")){
            // 登录操作，放行
            return chain.filter(exchange);
        }

        // 3、不是登录，获取token
        // 3.1 从请求参数获取token
        String token = request.getQueryParams().getFirst("token");
        // 3.2 从请求头获取token
        if (StringUtils.isEmpty(token)){
            token = request.getHeaders().getFirst("token");
        }
        // 3.1 从cookie中获取token
        if (StringUtils.isEmpty(token)){
            HttpCookie cookie = request.getCookies().getFirst("token");
            if (cookie != null){
                token = cookie.getValue();
            }
        }
        // 4、如果为null（未携带），不放行
        if (StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }


        try {
            // 5、校验token
            Claims claims = AppJwtUtil.getClaimsBody(token);    // 获取载荷
            int verifyToken = AppJwtUtil.verifyToken(claims);   // 是否过期
            if (verifyToken != 0 && verifyToken != -1){
                // token失效了
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            // 6、有效，获取载荷中的用户id
            Object id = claims.get("id");
            // 将id放入下次请求的头中
            request.mutate().header("userId", String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
            // 解析失败，假的token
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
