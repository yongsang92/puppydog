package com.puppydog.mall.dd.domain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puppydog.mall.config.Time;

import java.util.ArrayList;
import java.util.List;

@Table(name = "orders") 
// 테이블 이름을 order 로 안하고 orders 라고 한 이유는 order은 db 명령어와 겹치기 때문이다 
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order  extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    // 배송 정보
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;



    // 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }



   
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.GO) {
            throw new IllegalStateException("이미 배송이 시작되었습니다.");
        }
      
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return orderItems.stream()
            .mapToInt(OrderItem::getTotalPrice)
            .sum();
    }
    // 단일 상품에서 주문 버튼 클릭시 
    public static Order createOrder(Member member, Delivery delivery, OrderItem orderItem) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.addOrderItem(orderItem);
        return order;
    }
    // 주문리스트에서 주문 버튼 클릭시 
    public static Order createOrders(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

}
