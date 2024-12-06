package io.kang.example.security

import io.kang.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component

// 自定义认证提供者
@Component
class CustomAuthProvider: AuthenticationProvider {
    // 自动注入用户仓库
    @Autowired
    lateinit var userRepository: UserRepository

    // 定义用户名和角色映射
    private val auth = mapOf(Pair("test03", "ROLE_USER"), Pair("test02", "ROLE_ADMIN"))

    // 认证方法
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        // 获取用户名和密码
        val username = authentication.name
        val password = authentication.credentials.toString()

        // 根据用户名查询用户
        val user = userRepository.findByUserName(username)

        // 如果密码匹配，则返回认证信息
        if(user?.password.equals(password)) {
            // 获取用户角色
            val authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth[username])
            // 返回认证信息
            return UsernamePasswordAuthenticationToken(user, password, authorities)
        }

        return null
    }

    override fun supports(p0: Class<*>?): Boolean {
    // 支持的认证类型
        return true;
    }
}