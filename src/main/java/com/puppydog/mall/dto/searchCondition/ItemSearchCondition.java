package com.puppydog.mall.dto.searchCondition;
import lombok.Data;
import lombok.NoArgsConstructor;


// 아이템 조회시 사용될 조건  카테고리별, 가격순, 인기순
@Data
@NoArgsConstructor
public class ItemSearchCondition{

    String category;
    int price, sales;

}