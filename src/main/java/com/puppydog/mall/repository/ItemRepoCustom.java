package com.puppydog.mall.repository;
import java.util.List;

import com.puppydog.mall.dto.ItemShowDto;
import com.puppydog.mall.dto.WishItemDto;
import com.puppydog.mall.dto.searchCondition.ItemSearchCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepoCustom {


    List<WishItemDto> getWishItems(List<Long> ids);
    
    
    // 카테고리 별 
    Page<ItemShowDto> searchByCategory(ItemSearchCondition itemCondition,Pageable pageable);
    
    
    /* 기본으로 카테고리를 전달해야 한다 */
    // 싼 가격순 
    Page<ItemShowDto> searchByPriceASC(ItemSearchCondition itemCondition,Pageable pageable);

    // 비싼 가격순
    Page<ItemShowDto> searchByPriceDESC(ItemSearchCondition itemCondition,Pageable pageable);

    // 인기순(판매액 내림차순)
    Page<ItemShowDto> searchBySales(ItemSearchCondition itemCondition,Pageable pageable);
}