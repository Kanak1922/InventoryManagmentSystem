package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
