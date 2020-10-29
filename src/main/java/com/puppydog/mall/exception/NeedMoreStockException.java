package com.puppydog.mall.exception;

public class NeedMoreStockException extends RuntimeException {

    public NeedMoreStockException(Long id) {
        super("stock id: "+id+" is need more stocks");
    }
    
}
