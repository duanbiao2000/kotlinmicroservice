package io.kang.chapter03.basicsyntax

// 定义一个Person类，包含name和age属性，以及一个children属性，用于存储子对象
class Person(val name: String, var age: Int = 0) {
    var children: MutableList<Person> = mutableListOf<Person>();

    // 初始化函数，打印name和age属性
    init {
        println("name is $name, age is $age")
    }

    // 构造函数，用于创建子对象，并将子对象添加到父对象的children属性中
    constructor(name: String, age: Int, parent: Person) : this(name, age) {
        parent.children.add(this)
    }
}

// 主函数
fun main() {
    // 创建一个Person对象
    val person = Person("yuan")
    // 创建一个父对象，并将子对象添加到父对象的children属性中
    val parentPerson = Person("kang", 1, person)

    // 创建一个Derived对象，并调用其draw方法
    val derived = Derived("kang", 1)
    derived.draw()

    // 创建一个Demo对象，并打印其size和listToString属性
    val demo = Demo(MutableList<String>(10){"a"})
    println(demo.size)
    println(demo.listToString)

    // 创建一个User对象，并打印其name和age属性
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    val (name, age) = jack
    println("$name, $age years of age") // 输出 "Jane, 35 years of age"

    // 调用copyValue函数
    copyValue()

    // 创建一个数组，并填充字符串
    val arrayNulls = arrayOfNulls<Any>(2)
    fill(arrayNulls, "hello")
    println(arrayNulls.asList())
}


// 定义一个Base类，包含p和vertexCount属性，以及draw方法
open class Base(val p: Int) {
    open val vertexCount: Int = 0
    open fun draw() {
        println("Base.draw()")
    }
    // 构造函数，用于创建Base对象
    constructor(base: String, p: Int): this(p){
        println("base is $base")
    }
}
// 定义一个Derived类，继承自Base类，并重写vertexCount和draw方法
class Derived: Base {
    override var vertexCount = 4
    constructor(base: String, p: Int): super(base, p)

    override fun draw() {
        println("Derived.draw()")
    }
}

// 定义一个Demo类，包含aList属性，以及size和listToString属性
class Demo(val aList: MutableList<String>) {
    var size: Int = 0
        get() = aList.size
        set(value) {
            field = value
        }
    var listToString: String
        get() = aList.toString()
        set(value) {
            aList.add(value)
        }
}

// 定义一个Named接口，包含name属性
interface Named {
    val name: String
}

// 定义一个People接口，继承自Named接口，并包含firstName和lastName属性
interface People : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

// 定义一个Employee类，实现People接口，并实现其属性
class Employee(
        // 不必实现“name”
        override val firstName: String,
        override val lastName: String
) : People


// 定义一个User数据类，包含name和age属性
data class User(val name: String = "", val age: Int = 0)

// 定义一个Expr密封类，包含Const和Sum两个子类
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()

// 定义一个eval函数，用于计算Expr表达式的值
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}

// 定义一个ProtocolState枚举类，包含WAITING和TALKING两个状态，并重写signal方法
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

// 定义一个Box类，包含value属性
class Box<T>(t: T) {
    var value = t
}
val box: Box<Int> = Box<Int>(1)

// 定义一个Source接口，包含nextT方法
interface Source<out T> {
    fun nextT(): T
}

// 定义一个demo函数，用于演示泛型
fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
}

// 定义一个Comparable接口，包含compareTo方法
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

// 定义一个demo函数，用于演示泛型
fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

// 定义一个copy函数，用于复制数组
fun copy(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
// 定义一个copyValue函数，用于演示泛型
fun copyValue() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    copy(ints, any)
    println(any.asList())
    //   ^ 其类型为 Array<Int> 但此处期望 Array<Any>
}

// 定义一个fill函数，用于填充数组
fun fill(dest: Array<in String>, value: String) {
    for(i in dest.indices){
        dest[i] = value
    }
}