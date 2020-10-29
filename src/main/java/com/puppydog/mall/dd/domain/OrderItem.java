package com.puppydog.mall.dd.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puppydog.mall.dd.item.Item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private int orderPrice;
    private int count;

    public void cancel() {
        if (status == DeliveryStatus.READY) {
            getBackStock();
            setStatusCancel();
        }
        else{
            throw new IllegalStateException("취소 불가");
        }
    }

    public void setStatusCancel() {
        status = DeliveryStatus.CANCEL;
    }

    public void getBackStock() {
        item.addStock(count);

    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
    public void setStatusReady(){
        status=DeliveryStatus.READY;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        orderItem.setStatusReady();
        item.order(count);
        return orderItem;
    }
}
