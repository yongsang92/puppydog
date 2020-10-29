package com.puppydog.mall.cs.controller;
import javax.validation.Valid;

import com.puppydog.mall.cs.service.FileUploadService;
import com.puppydog.mall.cs.service.ItemService;
import com.puppydog.mall.dd.domain.Category;
import com.puppydog.mall.dd.domain.CategoryItem;
import com.puppydog.mall.dd.item.Item;
import com.puppydog.mall.form.ItemForm;
import com.puppydog.mall.repository.CategoryItemRepo;
import com.puppydog.mall.repository.CategoryRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final CategoryRepo cateRepo;
    private final CategoryItemRepo cateItemRepo;
    private final FileUploadService fileUploadService;

    // 아이템 등록
    @GetMapping("/item/new")
    public String registerSnackForm(Model model) {
        model.addAttribute("ItemForm", new ItemForm());
        return "regItem/itemRegForm";
    }

    // 아이템 등록
    @PostMapping("/item/new")
    public String registerSnack(@Valid ItemForm itemForm, @RequestParam("file") MultipartFile multipartFile,
            BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "regItem/itemRegForm";
        }
        String thumbnailPath = fileUploadService.upload(multipartFile);

        // 아이템
        Item item = itemForm.toEntity();

        item.setThumnailImgName(thumbnailPath);

        Category category = cateRepo.findOneByName(itemForm.getCategory());
        CategoryItem categoryItem = new CategoryItem();

        categoryItem.setRelation(category, item);
        cateItemRepo.save(categoryItem);

        return "redirect:/store/item/new";

    }

}