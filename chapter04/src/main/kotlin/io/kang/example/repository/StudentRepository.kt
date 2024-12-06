package io.kang.example.repository

import io.kang.example.entity.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.DeleteQuery
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

// 定义一个StudentRepository接口，继承自MongoRepository接口
interface StudentRepository: MongoRepository<Student, Long> {
    // 根据personId查询学生信息
    fun findByPersonId(personId: Long): Student?

    // 根据name和age查询学生信息，并按照pageable进行分页
    fun findByNameRegexAndAgeGreaterThanEqual(name: String, age: Int, pageable: Pageable): Page<Student>

    // 根据age1和age2删除学生信息
    @DeleteQuery(value = "{\"age\":{\"\$gte\":?0,\"\$lte\":?1}}")
    fun deleteByAgeIn(age1: Int, age2: Int)

    // 根据name和age查询学生信息，并按照pageable进行分页
    @Query(value = "{\"name\":{\"\$regex\":?0},\"age\":{\"\$gte\":?1}}")
    fun findByAgeIndividual(name: String ,age: Int, pageable: Pageable): Page<Student>
}