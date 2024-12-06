package io.kang.example.mq

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener
import org.apache.rocketmq.spring.core.RocketMQListener
import org.springframework.stereotype.Component

// 标识该类为一个Spring组件
@Component
// 标识该类为一个RocketMQ消息监听器，监听"kotlin-topic"主题，消费者组为"kotlin-consumer"
@RocketMQMessageListener(topic = "kotlin-topic", consumerGroup = "kotlin-consumer")
class MqConsumer: RocketMQListener<OrderPaidEvent> {

    // 重写RocketMQListener的onMessage方法，当接收到消息时调用
    override fun onMessage(p0: OrderPaidEvent?) {
        // 打印接收到的消息
        println("OrderPaidEventConsumer received: $p0")
    }
}