package com.zookao.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: zookao
 * Date: 2021-11-17
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UserTooManyException extends RuntimeException{
    public UserTooManyException() {
        super();
    }

    public UserTooManyException(String message) {
        super(message);
    }
}
