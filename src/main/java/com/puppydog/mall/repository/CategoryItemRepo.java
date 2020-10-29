package com.puppydog.mall.repository;
import com.puppydog.mall.dd.domain.CategoryItem;

import org.springframework.data.repository.CrudRepository;

public interface CategoryItemRepo extends CrudRepository<CategoryItem, Long> {
    
}