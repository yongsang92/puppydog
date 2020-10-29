package com.puppydog.mall.dd.domain;
import java.util.List;

import com.puppydog.mall.dto.OrderListItemShow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemRepoCustom {

    Page<OrderListItemShow> orderList(List<Long>orderIds,Pageable pageable);
    void cancelOrer(Long itemId,Long orderId);
}