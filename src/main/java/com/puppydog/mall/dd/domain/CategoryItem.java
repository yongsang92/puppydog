package com.puppydog.mall.dd.domain;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puppydog.mall.config.Time;
import com.puppydog.mall.dd.item.Item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@Entity
public class CategoryItem extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    public void setRelation(Category category, Item item){
        this.category=category;
        this.item=item;

        category.getCategoryItems().add(this);
        item.getCategoryItems().add(this);
    }

}
