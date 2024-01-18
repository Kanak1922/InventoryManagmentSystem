package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoryList=categoryService.getAllCategory();
        if(categoryList.size()==0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/getCategory")
    public ResponseEntity<?> getCategory(@RequestParam("id") Long id) {
        Category category= categoryService.getCategoryById(id);
        if(category==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        boolean res=categoryService.addCategory(category);
        if(res){
            return ResponseEntity.ok().build();
        }
        // return 409 status code if already exist category added or null .
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestParam("id") Long id, @RequestBody Category category) {
        boolean category1=categoryService.updateCategory(id, category);
        if(category1){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // delete feature removed
//    @DeleteMapping("/deleteCategory")
//    public void deleteCategory(@RequestParam("id") Long id) {
//        //productService.deleteByCategoryId(id);
//        categoryService.deleteCategory(id);
//    }
}
