package com.budget.budget2.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticateException extends AuthenticationException {

    public JwtAuthenticateException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticateException(String msg) {
        super(msg);
    }
}
