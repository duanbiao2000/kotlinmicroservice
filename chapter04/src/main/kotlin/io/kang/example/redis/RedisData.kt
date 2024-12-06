package io.kang.example.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.*
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.concurrent.TimeUnit

@Service
class RedisData {
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    fun saveString(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun saveStringWithExpire(key: String, value: String, expireSecond: Long) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expireSecond))
    }

    // 根据key获取字符串
    fun getString(key: String): String? {
        // 使用redisTemplate的opsForValue方法获取key对应的值
        return redisTemplate.opsForValue().get(key);
    }

    fun saveList(key: String, values: List<String>) {
        values.forEach { v ->
            redisTemplate.opsForList().leftPush(key, v)
        }
    }

    // 保存带有过期时间的列表
    fun saveListWithExpire(key: String, values: List<String>, expireSecond: Long) {
        // 遍历列表，将每个元素添加到Redis的列表中
        values.forEach { v ->
            redisTemplate.opsForList().leftPush(key, v)
        }
        // 设置列表的过期时间
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS)
    }

    fun getListValue(key: String, start: Long, end: Long): List<String>? {
        return redisTemplate.opsForList().range(key, start, end);
    }

    fun saveSet(key: String, values: Array<String>) {
        redisTemplate.opsForSet().add(key, *values)
    }

    fun saveSetWithExpire(key: String, values: Array<String>, expireSecond: Long) {
        redisTemplate.opsForSet().add(key, *values)
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS)
    }

    fun getSetValues(key: String): Set<String>? {
        return redisTemplate.opsForSet().members(key)
    }

    // 获取两个集合的差集
    fun getSetDiff(key1: String, key2: String): Set<String>? {
        // 使用redisTemplate的opsForSet方法获取两个集合的差集
        return redisTemplate.opsForSet().difference(key1, key2)
    }

    fun saveZset(key: String, values: Array<Pair<String, Double>>) {
        values.forEach { v ->  redisTemplate.opsForZSet().add(key, v.first, v.second)}
    }

    fun saveZsetWithExpire(key: String, values: Array<Pair<String, Double>>, expireSecond: Long) {
        values.forEach { v ->  redisTemplate.opsForZSet().add(key, v.first, v.second)}
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS)
    }

    // 根据分数范围获取有序集合中的元素
    fun getZsetRangeByScore(key: String, minScore: Double, maxScore: Double): Set<String>? {
        // 使用redisTemplate操作有序集合，根据分数范围获取元素
        return redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore)
    }

    // 保存哈希值
    fun saveHash(key: String, values: Map<String, String>) {
        // 使用redisTemplate操作哈希值
        redisTemplate.opsForHash<String, String>().putAll(key, values);
    }

    fun saveHashWithExpire(key: String, values: Map<String, String>, expireSecond: Long) {
        redisTemplate.opsForHash<String, String>().putAll(key, values);
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS)
    }

    fun getHashValues(key: String): List<String>? {
        return redisTemplate.opsForHash<String, String>().values(key);
    }
}