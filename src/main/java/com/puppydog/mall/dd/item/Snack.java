package com.puppydog.mall.dd.item;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("SNACK")
@PrimaryKeyJoinColumn(name = "SNACK_ID")
@NoArgsConstructor
@Getter
public class Snack extends Item {


    @Builder
    public Snack(String name,Integer price, Integer quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;

    
    }
}