package com.puppydog.mall.repository;
import java.util.List;

import com.puppydog.mall.dd.domain.OrderItem;
import com.puppydog.mall.dd.domain.OrderItemRepoCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepo  extends JpaRepository<OrderItem,Long>, OrderItemRepoCustom{


    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id=2")    
    List<OrderItem> findOrderList(List<Long> names);

}