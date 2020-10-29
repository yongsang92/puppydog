package com.puppydog.mall.customrepository;
import com.puppydog.mall.dd.item.Item;

import org.springframework.data.jpa.repository.JpaRepository;


// TODO FIX
// 커스텀 리포 상속하니깐 에러가 발생한다
public interface ItemRepo extends JpaRepository<Item,Long>, ItemRepoCustom{


    
}
 

 
