package com.puppydog.mall.dd.domain;
import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER("주문완료"), CANCEL("취소완료"),DONE("배송완료");

    public String status;

    private OrderStatus(String status) {
        this.status = status;
    }
    public String getName() { // 문자를 받아오는 함수
      return "wow";
    }

    @Override
    public String toString() {
        return status;
    } 
}
