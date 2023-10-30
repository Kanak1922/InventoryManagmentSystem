package com.kanak.ims.service;

import com.kanak.ims.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    void addCategory(Category category);

    void updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Category findByCategoryName(String name);

}
