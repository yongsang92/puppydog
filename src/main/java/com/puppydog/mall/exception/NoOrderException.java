package com.puppydog.mall.exception;

public class NoOrderException extends RuntimeException {

    public NoOrderException(Long id) {
        super("order id: "+id+" can't  be founded");
    }
    
}
