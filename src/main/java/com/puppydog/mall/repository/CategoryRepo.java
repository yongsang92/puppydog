package com.puppydog.mall.repository;
import com.puppydog.mall.dd.domain.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category findOneByName(String name);
}