package com.puppydog.mall.repository;

import com.puppydog.mall.dd.item.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long>, ItemRepoCustom{


    
}
 

 
