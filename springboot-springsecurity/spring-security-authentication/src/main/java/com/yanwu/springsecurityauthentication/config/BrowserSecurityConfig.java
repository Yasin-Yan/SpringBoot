package com.yanwu.springsecurityauthentication.config;

import com.yanwu.springsecurityauthentication.handler.MyAuthenticationFailureHandler;
import com.yanwu.springsecurityauthentication.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")   //登录跳转URL
                .loginProcessingUrl("/login")   //处理表单登录URL
                .successHandler(myAuthenticationSuccessHandler) //处理登录成功
                .failureHandler(myAuthenticationFailureHandler) //处理登录失败
                .and()
                .authorizeRequests()    //授权配置
                .antMatchers("/authentication/require", "/login.html", "/css/**").permitAll()  //登录跳转URL无需认证
                .anyRequest()           //所有请求
                .authenticated()       //都需要认证
                .and().csrf().disable();
    }
}
