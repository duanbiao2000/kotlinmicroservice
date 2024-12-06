// 定义一个包名为io.kang.example.repository的包
package io.kang.example.repository

// 导入io.kang.example.entity包中的User类
import io.kang.example.entity.User
// 导入org.springframework.data.jpa.repository包中的JpaRepository接口
import org.springframework.data.jpa.repository.JpaRepository
// 导入org.springframework.data.querydsl包中的QuerydslPredicateExecutor接口
import org.springframework.data.querydsl.QuerydslPredicateExecutor

// 定义一个接口UserDomainRepository，继承JpaRepository和QuerydslPredicateExecutor接口
interface UserDomainRepository: JpaRepository<User, Long>, QuerydslPredicateExecutor<User>