package com.puppydog.mall.cs.service;
import java.util.ArrayList;
import java.util.List;

import com.puppydog.mall.customrepository.ItemRepo;
import com.puppydog.mall.customrepository.OrderItemRepo;
import com.puppydog.mall.dd.domain.Delivery;
import com.puppydog.mall.dd.domain.DeliveryStatus;
import com.puppydog.mall.dd.domain.Member;
import com.puppydog.mall.dd.domain.Order;
import com.puppydog.mall.dd.domain.OrderItem;
import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.exception.MemberNotFoundException;
import com.puppydog.mall.exception.NoItemException;
import com.puppydog.mall.repository.OrderRepo;
import com.puppydog.mall.repository.WishItemRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final ItemRepo itemRepo;
    private final MemberService memberService;
    private final WishItemRepo wishItemRepo;

    /**
     * 단일 상품 즉시 구매 파라미터: 사용자의 아아디, 아이템의 아이디, 주문 수량
     */
    @Transactional
    public Long order(String memberId, Long itemId, Integer count) {

        Member member = memberService.findMember(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

        Item item = itemRepo.findById(itemId).orElseThrow(() -> new NoItemException(itemId));

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);// 추가

        delivery.setAddress(member.getAddress());

        // 주문-상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성 // 상품이 여러개면 여러개를 전달 해줘도 된다. 하지만 즉시 구매는 하나의 상품만 가능하기 때문에 하나만 전달
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepo.save(order);

        return order.getId();
    }

    /**
     * 주문 취소 
     */
    @Transactional
    @ResponseBody
    public String cancelOrder(Long itemId, Long orderId) {
       
        orderItemRepo.cancelOrer(itemId, orderId);

        // 주문 취소
       return "done";

    }

    @Override
    public List<Order> findAllOrder(String memberId) {
        return orderRepo.findByMemberId(memberId);
    }

    /*
     * 위시리스트에 있는 모든 상품 주문하는 메서드
     * 전달 받는 파라미터: 사용자 아이디
     */
    @Override
    public Long wishListOrder(String memberId) {
        Member member = memberService.findMember(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        List<Long> wishItemIds = wishItemRepo.findWishItemIds(memberId);
        List<Integer> wishItemCounts = wishItemRepo.findWishCount(memberId);
        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < wishItemIds.size(); i++) {
            Long itemId = wishItemIds.get(i);
            Item item = itemRepo.findById(itemId).orElseThrow(() -> new NoItemException(itemId));
            int count = wishItemCounts.get(i);
            OrderItem orderItem;

            if (item.getDiscount() != null) {
                Double asd = Math.floor(item.getPrice() * ((100 - item.getDiscount().doubleValue()) / 100));
                int discountedPrice = asd.intValue();

               orderItem = OrderItem.createOrderItem(item, discountedPrice, count);
            }else{
                orderItem=OrderItem.createOrderItem(item, item.getPrice(), count);
            }
            orderItems.add(orderItem);
        }
        Order order = Order.createOrders(member, delivery, orderItems);
        orderRepo.save(order);

        return null;
    }

}
