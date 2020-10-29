package com.puppydog.mall.customrepository;
import java.util.List;

import com.puppydog.mall.dd.domain.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// TODO FIX
// 여기도 커스텀 리포를 상속하는게 문제다
public interface OrderItemRepo  extends JpaRepository<OrderItem,Long>, OrderItemRepoCustom{


    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id=2")    
    List<OrderItem> findOrderList(List<Long> names);

}