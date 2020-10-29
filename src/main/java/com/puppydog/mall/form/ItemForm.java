package com.puppydog.mall.form;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.puppydog.mall.dd.item.Clothes;
import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.dd.item.Snack;
import com.puppydog.mall.dd.item.Toy;

import lombok.Data;

@Data
public class ItemForm {

    @NotEmpty(message = "필수항목 입니다")
    public String name;

    @NotEmpty(message = "필수항목 입니다")
    public String category;
    
    @NotNull(message = "필수항목 입니다")
    @Min(1)
    public Integer price;

    @NotNull(message = "필수항목 입니다")
    @Min(1)
    public Integer quantity;
    @NotEmpty(message = "필수항목 입니다")
    public String description;

    public Item toEntity(){
        if(category.equals("TOY")){
            return Toy.builder()
            .name(name)
            .category(category)
            .price(price)
            .quantity(quantity)
            .description(description).build();
        }
        if(category.equals("CLOTHES")){
            return Clothes.builder()
            .name(name)
            .category(category)
            .price(price)
            .quantity(quantity)
            .description(description).build();
        }
            return Snack.builder()
            .name(name)
            .category(category)
            .price(price)
            .quantity(quantity)
            .description(description).build();
    }
}