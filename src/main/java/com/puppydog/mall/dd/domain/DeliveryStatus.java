package com.puppydog.mall.dd.domain;
public enum DeliveryStatus {

    READY("준비"), GO("시작"),DONE("완료"),CANCEL("취소");

    private String status;

    private DeliveryStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}