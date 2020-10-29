package com.puppydog.mall.dd.item;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("CLOTHES")
@PrimaryKeyJoinColumn(name = "CLOTHES_ID")
@Getter
@NoArgsConstructor
public class Clothes extends Item {

    @Builder
    public Clothes(String name,Integer price, Integer quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;

    
    }
}