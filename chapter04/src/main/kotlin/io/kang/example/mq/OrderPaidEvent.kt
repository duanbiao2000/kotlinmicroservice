package io.kang.example.mq

import java.math.BigDecimal

// 定义一个订单支付事件类
class OrderPaidEvent(
        // 订单ID
        val orderId: String,
        // 支付金额
        val paidMoney: BigDecimal
){
    // 构造函数，默认订单ID为"0"，支付金额为0.0
    constructor():this("0", BigDecimal(0.0))

    // 重写toString方法，返回订单支付事件的字符串表示
    override fun toString(): String {
        return "OrderPaidEvent($orderId, $paidMoney)"
    }
}