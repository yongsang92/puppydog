package com.puppydog.mall.dd.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "id")
@Getter
@Entity
public class WishItem {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;


    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer count;


    public void setItemId(Long itemId){
        this.itemId= itemId;
    }

    public void setMember(Member member){
        
        this.member= member;
    }
    public void setCount(Integer count){
        this.count=count;
    }

    public void addCount(Integer count){
        this.count+=count;
    }
  


}