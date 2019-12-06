package com.yanwu.springsecurityvalidatecode.security;

import com.yanwu.springsecurityvalidatecode.handler.FailureAuthenticationHandler;
import com.yanwu.springsecurityvalidatecode.handler.SuccessAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//SpringSecurity核心配置
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    注入身份验证成功的处理器
    @Autowired
    SuccessAuthenticationHandler successAuthenticationHandler;

//    注入身份验证失败的处理器
    @Autowired
    FailureAuthenticationHandler failureAuthenticationHandler;

//    注入验证码过滤器，验证码过滤器在用户名密码过滤器之前过滤
    @Autowired
    ValidateCodeFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)    //添加验证码校验过滤器
                .formLogin()    //表单登录
//                http.httpBasic()
                .loginPage("/authentication/require") //登录跳转URL
                .loginProcessingUrl("/login")   //处理表单登录URL
//                .usernameParameter("username")  //需和表单中的name保持一致
//                .passwordParameter("password")
                .successHandler(successAuthenticationHandler)   //登录成功处理器
                .failureHandler(failureAuthenticationHandler)   //登陆失败处理器
                .and()
                .authorizeRequests()    //授权配置
                .antMatchers("/login.html", "/css/**", "/code/image", "/authentication/require",
                        "/js/**").permitAll()  //无需认证的路径
                .anyRequest()           //所有请求
                .authenticated()       //都需要认证
                .and().csrf().disable();    //关闭跨域保护
    }
}
