package com.puppydog.mall.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishItemDto {

    Long id;
    String name,thumbnail;

    Integer price, count,discount;

}