package io.kang.example.redis

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class RedisDataTest {
    // 注入RedisData对象
    @Autowired
    lateinit var redisData: RedisData

    // 测试保存带有过期时间的redis键值对
    @Test
    fun `save redis key value with expire time`() {
        runBlocking {
            // 保存键值对，并设置过期时间为2秒
            redisData.saveStringWithExpire("hello1", "helloWorld1", 2L)
            // 延迟2秒
            kotlinx.coroutines.delay(2 * 1000L)
        }
        println("get key")
        // 获取键值对
        val value = redisData.getString("hello1")
        // 断言值为空
        Assert.assertNull(value)
    }

    @Test
    fun `save and get redis value`() {
        val key = "hello"
        redisData.saveString(key, "helloWorld")
        val value = redisData.getString(key)
        Assert.assertEquals(value, "helloWorld")
    }

    @Test
    fun `save redis list with expire time`() {
        runBlocking {
            val values = arrayListOf("hi1", "hi2", "hi3")
            redisData.saveListWithExpire("listKey1", values, 2L)
            kotlinx.coroutines.delay(2 * 1000L)
        }

        val values = redisData.getListValue("listKey1", 0L, 3L)
        Assert.assertEquals(values?.size, 0)
    }

    @Test
    fun `save and get list values`() {
        val key = "listKey"
        redisData.saveList(key, arrayListOf("hi1", "hi2", "hi3"))
        val values = redisData.getListValue("listKey", 0L, 1L)
        Assert.assertEquals(2, values?.size)
    }

    @Test
    fun `save redis set with expire time`() {
        runBlocking {
            val values = arrayOf("hello", "hello", "world")
            redisData.saveSetWithExpire("setKey3", values, 2L)
            kotlinx.coroutines.delay(2 * 1000L)
        }

        val values = redisData.getSetValues("setKey3");
        Assert.assertEquals(values?.size, 0)
    }

    @Test
    fun `save and get redis two set diff`() {
        redisData.saveSet("setKey1", arrayOf("hello", "hello", "world", "wide"))
        redisData.saveSet("setKey2", arrayOf("hello", "hello", "world", "women"))
        val diffSet = redisData.getSetDiff("setKey1", "setKey2")
        Assert.assertEquals(diffSet?.size, 1)
    }

    @Testc
    // 测试保存带有过期时间的redis zset
    fun `save redis zset with expire time`() {
        runBlocking {
            // 定义一个数组，包含三个Pair对象，分别表示姓名和分数
            val values = arrayOf(Pair("xiaoming",98.0), Pair("xiaoli", 90.0), Pair("wangming", 100.0))
            // 调用redisData的saveZsetWithExpire方法，保存带有过期时间的zset
            redisData.saveZsetWithExpire("zsetKey2", values, 2L)
            // 延迟2秒
            kotlinx.coroutines.delay(2_000)
        }

        // 调用redisData的getZsetRangeByScore方法，获取分数在95.0到99.0之间的zset
        val values = redisData.getZsetRangeByScore("zsetKey2", 95.0, 99.0);
        // 断言获取到的zset大小为0
        Assert.assertEquals(values?.size, 0)
    }

    @Test
    fun `saven and get redis zset values by score`() {
        // 保存zsetKey1的值
        redisData.saveZset("zsetKey1", arrayOf(Pair("xiaoming",98.0), Pair("xiaoli", 90.0), Pair("wangming", 100.0)))
        // 获取zsetKey1中分数在95.0到100.0之间的值
        val values = redisData.getZsetRangeByScore("zsetKey1", 95.0, 100.0)
        // 断言获取到的值数量为2
        Assert.assertEquals(2, values?.size)
    }

    @Test
    fun `save redis hashs with expire time`() {
        // 使用runBlocking函数启动一个协程
        runBlocking {
            // 创建一个Map，包含两个键值对
            val aMap = mapOf(Pair("key1","value1"), Pair("key2", "value2"))
            // 调用redisData的saveHashWithExpire方法，将Map保存到redis中，并设置过期时间为2秒
            redisData.saveHashWithExpire("hashKey2", aMap, 2L)
            // 使用kotlinx.coroutines.delay函数延迟2秒
            kotlinx.coroutines.delay(2_000)
        }

        // 调用redisData的getHashValues方法，获取hashKey2对应的值
        val values = redisData.getHashValues("hashKey2")
        // 使用Assert.assertEquals方法断言values的大小为0
        Assert.assertEquals(0, values?.size)
    }

    @Test
    fun `save and get redis hash values`() {
        val aMap = mapOf(Pair("key1","value1"), Pair("key2", "value2"))
        redisData.saveHash("hashKey1", aMap)
        val values = redisData.getHashValues("hashKey1")
        Assert.assertEquals(2, values?.size)
    }
}