package io.kang.chapter03.basicsyntax

import kotlin.reflect.KProperty

interface Delagate {
    val message: String
    fun print()
}

// 定义一个DelagateImpl类，继承自Delagate接口
class DelagateImpl(val x: Int) : Delagate {
    // 重写Delagate接口中的message属性，返回一个字符串
    override val message = "BaseImpl: x = $x"
    // 重写Delagate接口中的print()方法，打印message属性
    override fun print() { println(message) }
}

class DelagateDerived(b: Delagate) : Delagate by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"
}

fun main() {
    val b = DelagateImpl(10)
    val derived = DelagateDerived(b)
    derived.print()
    println(derived.message)

    val e = Example()
    //io.kang.chapter03.basicsyntax.Example@73a8dfcc, thank you for delegating 'p' to me!
    println(e.p)
    //NEW has been assigned to 'p' in io.kang.chapter03.basicsyntax.Example@73a8dfcc.
    e.p = "NEW"
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}