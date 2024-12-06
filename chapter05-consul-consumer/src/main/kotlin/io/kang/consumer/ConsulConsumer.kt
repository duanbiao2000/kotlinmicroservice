package io.kang.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

// 启动类
@SpringBootApplication
// 启用服务发现
@EnableDiscoveryClient
// 启用Feign客户端
@EnableFeignClients
class ConsulConsumer {
    // 创建RestTemplate Bean
    @Bean
    // 启用负载均衡
    @LoadBalanced
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    // 运行Spring Boot应用
    runApplication<ConsulConsumer>(*args)
}