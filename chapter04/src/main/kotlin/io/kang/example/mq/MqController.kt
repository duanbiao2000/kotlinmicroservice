package io.kang.example.mq

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MqController {
    // 注入MqProducer
    @Autowired
    // 声明一个延迟初始化的变量mqProducer，类型为MqProducer
    lateinit var mqProducer: MqProducer

    // 发送消息
    @PostMapping("/mq/send")
    fun sendMsg(@RequestBody orderPaidEvent: OrderPaidEvent) {
        mqProducer.sendMessage(orderPaidEvent)
    }

    // 发送带标签的消息
    @PostMapping("/mq/send/tag")
    // 发送消息标签
    fun sendMsgTag(@RequestBody orderPaidEvent: OrderPaidEvent) {
        mqProducer.sendMessageWithTag(orderPaidEvent)
    }

    // 转换并发送消息
    @PostMapping("/mq/convertAndSend")
    fun convertAndSendMsg(@RequestBody orderPaidEvent: OrderPaidEvent) {
        mqProducer.convertAndSendMessage(orderPaidEvent)
    }

    // 异步发送消息
    @PostMapping("/mq/asyncSend")
    fun asyncAndSendMsg(@RequestBody orderPaidEvent: OrderPaidEvent) {
        mqProducer.asyncSendMessage(orderPaidEvent)
    }

    // 异步批量发送消息
    @PostMapping("/mq/asyncBatchSend")
    fun asyncAndBatchSendMsg(@RequestBody orderPaidEvents: List<OrderPaidEvent>) {
        mqProducer.syncSendBatchMessage(orderPaidEvents)
    }

    // 同步发送消息
    @PostMapping("/mq/syncSend")
    fun syncSendMsg(@RequestBody orderPaidEvent: OrderPaidEvent) {
        mqProducer.syncSendMessage(orderPaidEvent)
    }
}