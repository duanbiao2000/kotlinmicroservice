package io.kang.example.data

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.time.Duration

@SpringBootTest
@RunWith(SpringRunner::class)
class RedisOperationTest {
    // 注入RedisTemplate
    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    // 测试Redis添加操作
    @Test
    fun `test redis add`() {
        // 向Redis中添加一个键值对，键为"key1"，值为"kk"，过期时间为10秒
        redisTemplate.opsForValue().set("key1", "kk", Duration.ofSeconds(10))
        // 断言获取到的值与添加的值相等
        Assert.assertEquals("kk", redisTemplate.opsForValue().get("key1"))
    }

}