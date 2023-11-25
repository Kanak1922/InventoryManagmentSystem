package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/getCategory")
    public Category getCategory(@RequestParam("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/addCategory")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("/updateCategory")
    public void updateCategory(@RequestParam("id") Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/deleteCategory")
    public void deleteCategory(@RequestParam("id") Long id) {
        productService.deleteByCategoryId(id);
        categoryService.deleteCategory(id);
    }
}
