package com.puppydog.mall.cs.service;
import java.util.List;

import com.puppydog.mall.dd.domain.Order;


public interface OrderService {
    /**
     * 단일 상품 즉시 주문 
     * 파라미터: 사용자의 아아디, 아이템의 아이디, 주문 수량
     */
    public Long order(String memberId, Long itemId, Integer count);


    /* 
    위시리스트에 있는 모든 상품 주문
    파라미터: 사용자 아이디
    상품을 주문할때 상품의 가격을 그 상황에 맞게 해야한다
    절대적인 가격이 아니라 그 당시 주문한 가격
    */
    public Long wishListOrder(String memberId);

    /**
     * 주문 취소
     */
    public String cancelOrder(Long itemId, Long orderId);


    public List<Order> findAllOrder(String memberId);
  
}
