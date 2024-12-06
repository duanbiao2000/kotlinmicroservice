package io.kang.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// 定义一个ConsulController类，用于处理Consul相关的请求
@RestController
class ConsulController {
    // 定义一个GET请求，路径为hello/consul，返回字符串"Hello Consul"
    @GetMapping("hello/consul")
    fun helloConsul(): String {
        return "Hello Consul"
    }

}