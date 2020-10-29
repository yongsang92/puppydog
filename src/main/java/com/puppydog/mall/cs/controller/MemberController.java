package com.puppydog.mall.cs.controller;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.puppydog.mall.config.PageFilter;
import com.puppydog.mall.config.PageMaker;
import com.puppydog.mall.cs.service.OrderService;
import com.puppydog.mall.customrepository.ItemRepo;
import com.puppydog.mall.customrepository.OrderItemRepo;
import com.puppydog.mall.dd.domain.Order;
import com.puppydog.mall.dto.OrderListItemShow;
import com.puppydog.mall.dto.WishItemDto;
import com.puppydog.mall.repository.WishItemRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final OrderService orderService;
    private final OrderItemRepo orderItemRepo;
    private final WishItemRepo wishItemRepo;
    private final ItemRepo itemRepo;

    // 사용자의 주문목록 보여주기 
    @GetMapping("/orderList")
    public String idcheck(Principal principal, Model model, PageFilter pageFilter) {

        String memberId = principal.getName();
        // 사용자 아이디

        List<Order> order = orderService.findAllOrder(memberId);
        // 사용자의 모든 주문 찾기

        List<Long> orderIds = order.stream().map(o -> o.getId()).collect(Collectors.toList());
        // 사용자의 모든 주문에서 주문 아이디만 추출

        Pageable pageable = pageFilter.makePageable();
        // 주문 목록 리스트도 페이징을 적용해서 보여줘야 한다


            Page<OrderListItemShow> result = orderItemRepo.orderList(orderIds, pageable);
            System.out.println("*** 주문 목록 출력 ***");
            result.getContent().forEach(s -> System.out.println(s));

            model.addAttribute("result", new PageMaker<>(result));
        return "user/orderList";
    }

    // 사용자의 위시리스트를 보여준다.
    @GetMapping("/showwishList")
    public String buketList(Principal principal, Model model) {

        String memberId = principal.getName();

        List<Long> wishItemIds = wishItemRepo.findWishItemIds(memberId);

        List<Integer> wishItemCounts = wishItemRepo.findWishCount(memberId);

        Iterator<Integer> iter = wishItemCounts.iterator();
        
        List<WishItemDto> result = itemRepo.getWishItems(wishItemIds);
        System.out.println("찾아온 아이템 출력");
        result.forEach(s -> {
            Integer count = iter.next();
            s.setCount(count);
        });
        model.addAttribute("result", result);

        return "user/wishList";
    }

    // 사용자의 위시리스트에서 아이템을 삭제한다 
    @PostMapping("/wishItemDel")
    @Transactional
    @ResponseBody
    public String wishItemDel(@RequestParam Long itemId,Principal principal){

        String memberId = principal.getName();
        wishItemRepo.delWishItem(itemId,memberId);
        return "wishItemDel";
    }

}