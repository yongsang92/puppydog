package com.puppydog.mall.cs.service;
import java.util.List;
import java.util.Optional;

import com.puppydog.mall.customrepository.ItemRepo;
import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.dd.item.Snack;
import com.puppydog.mall.repository.SnackRepo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SnackRepo snackRepo;
    private final ItemRepo itemRepo;

    @Override
    public List<Snack> findAllSnack() {
        return snackRepo.findAll();

    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepo.findById(id);
    }


}
