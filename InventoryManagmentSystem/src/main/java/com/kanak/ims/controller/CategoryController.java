package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;

    private static final Logger LOGGER= LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> categoryList=categoryService.getAllCategory();
            if (categoryList.size()==0) {
                return new ResponseEntity<>(emptyReturn(),HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while Fetching category: {}",e.getMessage());
            return new ResponseEntity<>("Error while Fetching category",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCategory")
    public ResponseEntity<?> getCategory(@RequestParam("id") Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                return new ResponseEntity<>(emptyReturn(),HttpStatus.OK);
                //return new ResponseEntity<>("category not found with id : "+id,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while Fetching category by ID: {}",e.getMessage());
            return new ResponseEntity<>("Error while Fetching category by ID",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            boolean res = categoryService.addCategory(category);
            if (res) {
                return new ResponseEntity<>("category added successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Category already added",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            LOGGER.error("Error while adding category: {}",e.getMessage());
            return new ResponseEntity<>("Error while adding category",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestParam("id") Long id, @RequestBody Category category) {
        try {
            boolean category1 = categoryService.updateCategory(id, category);
            if (category1) {
                return new ResponseEntity<>("Category updated successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Category already exists or getting conflict",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            LOGGER.error("Error while updating category: {}",e.getMessage());
            return new ResponseEntity<>("Error while updating category",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byName")
    public ResponseEntity<?> findCategoryByName(@RequestParam("name") String name) {
        try {
            Category category = categoryService.findByProductCategory(name);
            if (category == null) {
                return new ResponseEntity<>(emptyReturn(),HttpStatus.OK);
            }
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with name :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with name",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Object> emptyReturn(){
        return new ArrayList<>();
    }


}
