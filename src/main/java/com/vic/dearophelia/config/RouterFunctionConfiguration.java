package com.vic.dearophelia.config;

import com.vic.dearophelia.domain.User;
import com.vic.dearophelia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数 配置
 *
 * @author vicdor
 * @Configuration 表示类所处的对象是一个配置对象
 * spring3以后提供 逐一替代xml文件
 * 可以理解为java版的xml文件
 * @create 2018-02-11 16:57
 */
@Configuration
public class RouterFunctionConfiguration {
    /**
     * Servlet
     * 请求接口：ServletRequest 或 HttpServletRequest
     * 响应接口：ServletResponse 或 HttpServletResponse
     * Spring 5.0 重新定义服务请求和响应接口
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 既可支持servlet规范，也可以支持自定义，比如Netty (Web Server)
     * <p>
     * 以本例：
     * 定义GET请求，并且返回所有用户对象 URI:/user/persons
     * Flux 是 0 - N 个对象集合
     * Mono 是 0 - 1 个对象集合
     * Reactive 中的 Flux 或者 Mono 主要是异步处理 （非阻塞）
     * 集合对象基本上是同步处理 （阻塞）
     * Flux 或 Mono 都是 Publisher
     *
     * 异步方式 提高吞吐量
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/user/persons"),
                request -> {
                    //返回所有用户对象
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    Mono<ServerResponse> responseMono = ServerResponse.ok().body(userFlux, User.class);
                    return responseMono;
                });
    }
}
