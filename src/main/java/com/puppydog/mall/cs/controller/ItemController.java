package com.puppydog.mall.cs.controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.puppydog.mall.config.PageFilter;
import com.puppydog.mall.config.PageMaker;
import com.puppydog.mall.cs.service.ItemService;
import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.dto.ItemShowDto;
import com.puppydog.mall.exception.NoItemException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequestMapping("item")
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final EntityManager em;

    // 모든 아이템 조회    
    @GetMapping("/all")
    public String all(PageFilter pageFilter, Model model,String type,String sort) {

        if(StringUtils.isEmpty(sort))
            sort="asc";
        if(StringUtils.isEmpty(type)) 
            type="Item";  

        Pageable pageable = pageFilter.makePageable();
       
        // TODO INFO : ItemShowDto의 패키지 명을 주었다
        String part1="select new com.puppydog.mall.dto.ItemShowDto(i.id, i.name, i.thumbnail, i.price, i.sales, i.discount, i.regdate) from ";
        String part2=" i ";
        String part3=" order by i.price "; 
        
        String sql=part1.concat(type).concat(part2).concat(part3).concat(sort);
        
        if(sort.equals("regdate")){
            part3=" order by i.regdate desc ";
            sort=" regdate ";
            sql=part1.concat(type).concat(part2).concat(part3);
        }
        


        TypedQuery<ItemShowDto> query= em.createQuery(sql,ItemShowDto.class);
        Long total=Long.valueOf(query.getResultList().size());

        List<ItemShowDto> content= query
        .setFirstResult(pageable.getPageNumber()*12) // offset 설정 // 한페이지에 12개씩 보여주니깐 *12를 해야한다
        .setMaxResults(pageable.getPageSize()) // size 설정
        .getResultList();
        
        Page<ItemShowDto> result=new PageImpl<>(content,pageable,total);
     
        model.addAttribute("result", new PageMaker<>(result));
        model.addAttribute("type",type);
        model.addAttribute("sort", sort);
        return "item/itemList";
    }

    // 단일 아이템 조회
    @GetMapping("/{id}")
    public String single(@PathVariable Long id,Model model) {
        Item item = itemService.findById(id).orElseThrow(() -> new NoItemException(id));
        model.addAttribute("result",item);
        return "item/item";
    }

    // 배송상태 조회
    @GetMapping("/deliveryStatus")
    public String deliveryStatus(String deliveryStatus,Long itemId, Long orderId, Model model) {
        model.addAttribute("itemId", itemId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("deliveryStatus", deliveryStatus);

        return "item/deliveryStatus";
    }

}