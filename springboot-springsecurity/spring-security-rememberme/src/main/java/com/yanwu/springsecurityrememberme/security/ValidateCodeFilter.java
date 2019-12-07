package com.yanwu.springsecurityrememberme.security;

import com.yanwu.springsecurityrememberme.controller.ValidateController;
import com.yanwu.springsecurityrememberme.entity.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器继承OncePerRequestFilter实现doFilterInternal方法，只过滤一次
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

//    注入AuthenticationFailureHandler的实现类FailureAuthenticationHandler
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

//   HttpSessionSessionStrategy对象封装了一些处理Session的方法，包含了setAttribute、getAttribute和removeAttribute方法
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        判断请求URL和请求方法
        if(StringUtils.equalsIgnoreCase("/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase("POST", httpServletRequest.getMethod())){
            try{
                validateCode(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
//        传给下一个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException{

//        从session中取得存入的验证码对象
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);

//        从登录请求中取得验证码字符串
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if(StringUtils.isEmpty(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空!");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在!");
        }
        if(codeInSession.isExpire()){
            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期!");
        }
        if(!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException("验证码不正确!");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);

    }
}
