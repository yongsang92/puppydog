package com.puppydog.mall.exception;

public class NoItemException extends RuntimeException {

    public NoItemException(Long id) {
        super("stock id: "+id+" can't  be founded");
    }
    
}
