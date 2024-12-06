// 定义一个包名为io.kang.example.repository的包
package io.kang.example.repository

// 导入io.kang.example.entity包中的Item类
import io.kang.example.entity.Item
// 导入org.springframework.data.elasticsearch.repository包中的ElasticsearchRepository接口
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

// 定义一个接口ItemRepository，继承ElasticsearchRepository接口
interface ItemRepository: ElasticsearchRepository<Item, Long>