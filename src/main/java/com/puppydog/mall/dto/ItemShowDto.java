package com.puppydog.mall.dto;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 화면에서 보여줄거 상품이름, 가격, )
// id는 아이템을 클릭했을때 상품을 조회해야 하니깐 필요 
// 썸네일 위치는  사진위치를 알아야 하니깐 필요
@Data
@NoArgsConstructor
public class ItemShowDto {


    public Long id;
    public String name, thumbnail;
    public Integer price, sales,discount;
    public LocalDateTime regdate;


    @Builder
    public ItemShowDto(Long id, String name, String thumbnail, Integer price, Integer sales,Integer discount, LocalDateTime regdate) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.sales = sales;
        this.discount=discount;
        this.regdate=regdate;
    }
    
 


}