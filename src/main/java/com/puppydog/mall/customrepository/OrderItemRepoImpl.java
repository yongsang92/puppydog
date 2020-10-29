package com.puppydog.mall.customrepository;

import java.util.List;

import javax.persistence.EntityManager;

import com.puppydog.mall.dd.domain.OrderItem;
import com.puppydog.mall.dto.OrderListItemShow;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import static com.puppydog.mall.dd.domain.QOrderItem.orderItem;
public class OrderItemRepoImpl implements OrderItemRepoCustom {

    private JPAQueryFactory qf;

    public OrderItemRepoImpl(EntityManager em) {
        qf = new JPAQueryFactory(em);
    }
    // 사용자의 주문목록을 만든다
    @Override
    public Page<OrderListItemShow> orderList(List<Long> orderIds, Pageable pageable) {
        QueryResults<OrderListItemShow> result= 
        qf.select(Projections.fields(OrderListItemShow.class
            ,orderItem.count,orderItem.orderPrice.as("itemPrice")
            ,orderItem.item.id.as("itemId"),orderItem.item.thumbnail,orderItem.item.name.as("itemName")
            ,orderItem.order.regdate.as("orderDate"),orderItem.status.as("deliveryStatus")
            ,orderItem.order.id.as("orderId")
        
        ))
            .from(orderItem)
            .where(orderItem.order.id.in(orderIds))
            .orderBy(orderItem.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize()).fetchResults();
    
            List<OrderListItemShow> content=result.getResults();
            Long count= result.getTotal();

            return new PageImpl<>(content, pageable, count);
    }



    @Override
    public void cancelOrer(Long itemId, Long orderId) {
        OrderItem result = qf.selectFrom(orderItem)
            .where(orderItem.item.id.eq(itemId), orderItem.order.id.eq(orderId))
            .fetchOne();
        result.cancel();
    }

   

}