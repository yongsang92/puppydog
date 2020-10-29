package com.puppydog.mall.dto;
import java.time.LocalDateTime;

import com.puppydog.mall.dd.domain.DeliveryStatus;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 주문목록 화면에서 보여줄 정보 클래스
// 주문목록이랑 구매 내역이랑 다른점 : 배송상태를 조절 가능여부
@Data
@NoArgsConstructor
public class OrderListItemShow {

    public Long itemId,orderId;
    public String itemName, thumbnail;
    public Integer count, itemPrice;
    public LocalDateTime orderDate;
    public DeliveryStatus deliveryStatus;
    
    @Builder
    public OrderListItemShow(Long itemId,Long orderId, String itemName, String thumbnail, Integer count
    , Integer itemPrice,DeliveryStatus deliveryStatus,LocalDateTime orderDate) {
        this.itemId = itemId;
        this.orderId=orderId;
        this.itemName = itemName;
        this.thumbnail = thumbnail;
        this.count = count;
        this.itemPrice = itemPrice;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
    }
  
}