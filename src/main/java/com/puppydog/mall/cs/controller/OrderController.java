package com.puppydog.mall.cs.controller;
import java.security.Principal;

import javax.transaction.Transactional;

import com.puppydog.mall.cs.service.ItemService;
import com.puppydog.mall.cs.service.MemberService;
import com.puppydog.mall.cs.service.OrderService;
import com.puppydog.mall.dd.domain.Member;
import com.puppydog.mall.dd.domain.WishItem;
import com.puppydog.mall.repository.WishItemRepo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final WishItemRepo wishItemRepo;
    private final MemberService meberService;
    private final ItemService itemService;

    /*
     * 즉시 구매 버튼을 클릭 전달받을 것들 : 아이템 아이디 , 아이템 수량
     */
    @PostMapping("/buynow")
    @ResponseBody
    public Integer singleBuyNow(@RequestParam Long itemId, @RequestParam Integer count, Principal principal) {
        
        Integer max = itemService.findById(itemId).get().getQuantity();
        if (count > max) {
            return max;
        } else {
            String memberId = principal.getName();
            orderService.order(memberId, itemId, count);
        }
        return -1;
        
    }
    /*
     * 장바구니 버튼 클릭 전달받을 것들: 아이템 아이디, 아이템 수량 
     * WishItem 테이블에 사용자 아이디, 아이템 아이디,수량 설정
     * 
     * [만약 장바구니에 같은 아이템이 있다면?] -> 기존 아이템에 수량을 추가
     */
    @PostMapping("/wishlist")
    @ResponseBody
    public String wishList(@RequestParam Long itemId, @RequestParam Integer count, Principal principal) {
        String memberId = principal.getName();
        WishItem wi = wishItemRepo.already(memberId, itemId);
        if (wi == null) {
            WishItem wishItem = new WishItem();
            Member m = meberService.findMember(memberId).get();
            wishItem.setItemId(itemId);
            wishItem.setCount(count);
            wishItem.setMember(m);
            wishItemRepo.save(wishItem);
            return "";
        }
        wi.addCount(count);
        wishItemRepo.save(wi);
        // 있다면 수량 추가
        return "";
    }

    /*
     * 위시리스트 html에서 주문 버튼을 누르면 위시리스트에 있는 모든 아이템을 주문ㄱ
     */
    @PostMapping("/wishlistBuy")
    @ResponseBody
    public String wishListBuyNow(Principal principal) {
        String memberId = principal.getName();
        orderService.wishListOrder(memberId);
        return "";
    }

    @PostMapping("/wishlistCountModi")
    @ResponseBody
    public Integer countModi(@RequestParam Long itemId, @RequestParam Integer count, Principal principal) {
   
        Integer max = itemService.findById(itemId).get().getQuantity();
        // 재고보다 많은 수량이 들어오면 에러
        if (count > max) {
            return max;
        } else {
            String memberId = principal.getName();
            WishItem wishItem = wishItemRepo.already(memberId, itemId);
            wishItem.setCount(count);
            wishItemRepo.save(wishItem);
        
        }
        return -1;
    }

    @Transactional
    @PostMapping("cancel")
    @ResponseBody
    public String cancelOrder(Long itemId, Long orderId) {
        return orderService.cancelOrder(itemId, orderId);
        // return " 아이템 아이디: "+itemId+" 오더 아이디  : "+orderId;
    }

}
