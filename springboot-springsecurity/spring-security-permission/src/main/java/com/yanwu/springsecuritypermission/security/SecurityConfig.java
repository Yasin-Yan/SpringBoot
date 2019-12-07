package com.yanwu.springsecuritypermission.security;

import com.yanwu.springsecuritypermission.handler.FailureAuthenticationHandler;
import com.yanwu.springsecuritypermission.handler.MyAuthenticationAccessDeniedHandler;
import com.yanwu.springsecuritypermission.handler.SuccessAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

//SpringSecurity核心配置
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启授权注解
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

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    DataSource dataSource;

    @Autowired
    MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

//    配置token持久化对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);  //指定数据源
        jdbcTokenRepository.setCreateTableOnStartup(false);//是否启动项目时创建保存token信息的数据表，这里手动创建
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)    //添加验证码校验过滤器
                .formLogin()    //表单登录
    //                http.httpBasic()
                    .loginPage("/authentication/require") //登录跳转URL
                    .loginProcessingUrl("/login")   //处理表单登录URL
    //                .usernameParameter("username")  //需和表单中的name保持一致
    //                .passwordParameter("password")
                    .successHandler(successAuthenticationHandler)   //登录成功处理器
                    .failureHandler(failureAuthenticationHandler)   //登陆失败处理器
                    .and()
                .rememberMe()   //记住我功能
                    .tokenRepository(persistentTokenRepository())   //配置token持久化仓库
                    .tokenValiditySeconds(3600) //remember过期时间，单位为秒
                    .userDetailsService(userDetailService)  //处理自动登录逻辑
                    .and()
                .authorizeRequests()    //授权配置
                    .antMatchers("/login.html",
                            "/css/**",
                            "/code/image",
                            "/authentication/require",
                            "/js/**").permitAll()  //无需认证的路径
                    .anyRequest()           //所有请求
                    .authenticated()       //都需要认证
                    .and()
                .logout()   //退出登录行为
                    .logoutUrl("/logout")   //退出登录的URL
                    .logoutSuccessUrl("/login.html")    //退出成功后跳转的URL
                    .deleteCookies("JSESSIONID")    //退出成功后删除名称为JSESSIONID的cookies
//                    .logoutSuccessHandler(logOutSuccessHandler)   //指定退出成功后的处理器
                    .and()
                .csrf().disable();    //关闭跨域保护
    }
}
