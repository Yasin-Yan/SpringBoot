package com.yanwu.springsecuritypermission.security;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 3593658819781337160L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
