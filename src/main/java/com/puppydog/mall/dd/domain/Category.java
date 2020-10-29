package com.puppydog.mall.dd.domain;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
@ToString(exclude = {"categoryItems","parent"})
@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;


    // 연결 테이블
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<CategoryItem> categoryItems=new ArrayList<>();



    // **********************    
    // 카테고리 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 카테고리 연관관계 설정
    @JsonIgnore
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    //  @JoinColumn(name = "parent_id")을 해주면 안된다 
    private List<Category> child = new ArrayList<>();
    // **********************    



    // 카테고리 연관관계 설정 메서드
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParentCategory(this);
    }

    private void setParentCategory(Category category){
        this.parent=category;
    }

    public void setName(String name){
        this.name=name;
    }

}
