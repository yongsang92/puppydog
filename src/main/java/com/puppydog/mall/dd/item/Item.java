package com.puppydog.mall.dd.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.puppydog.mall.config.Time;
import com.puppydog.mall.dd.domain.CategoryItem;
import com.puppydog.mall.exception.NeedMoreStockException;

import lombok.Getter;
import lombok.ToString;
@ToString(exclude = {"categoryItems"})
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Entity
public abstract class Item  extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    // 이름
    protected String name;

    // 가격
    protected Integer price;
    // 수량
    protected Integer quantity;

    protected Integer discount;
    // 설명
    protected String description;

    // 매출액
    protected Integer sales=0;

    // 썸네일 사진 위치
    protected String thumbnail;

    // 카테고리를 여러개 선택 할 수 있다
    // 예를 들어  블랙진은 하의, 청바지 에 속하는 아이템이니 하의, 청바지 두개의 카테고리를 선택해야 한다 
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "item")
    protected List<CategoryItem> categoryItems=new ArrayList<>();

    public void setThumnailImgName(String thumbnail){
        this.thumbnail=thumbnail;
    }


    /*
     * 재고 추가
     */
    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    /*
     * 주문
     */
    public void order(int count) {
        this.quantity -= count;
        if (this.quantity < 0) {
            this.quantity += count;
            throw new NeedMoreStockException(id);
        }
        addSales(count);
    }

    /*
     * 매출액 추가
     */
    private void addSales(int count) {
        
        sales += price * count;
    }
  
}