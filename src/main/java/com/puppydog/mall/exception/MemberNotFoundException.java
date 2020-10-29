package com.puppydog.mall.exception;
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String memberId) {
        super("member id: "+memberId+" can't  be founded");
    }
    
}
