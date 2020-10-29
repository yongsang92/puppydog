package com.puppydog.mall.cs.service;
import java.util.List;
import java.util.Optional;

import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.dd.item.Snack;



public interface ItemService {
    List<Snack> findAllSnack();

    Optional<Item> findById(Long id);
    
}
