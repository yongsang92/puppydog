package com.puppydog.mall.repository;

import com.puppydog.mall.dd.item.Toy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ToyRepo extends JpaRepository<Toy,Long>{

    @Query("select t.id, t.name, t.thumbnail, t.price, t.sales from Toy t order by t.price asc")
    public Page<Toy> findAll(Pageable Pageable);   // 컬럼 추출하면 Object[]로 받아야 할 텐데 안될건데 Toy로 그냥 받으면 일단 해보자
}