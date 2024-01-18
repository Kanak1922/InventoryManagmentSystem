package com.kanak.ims.service;

import com.kanak.ims.model.Category;
import com.kanak.ims.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();

    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public boolean addCategory(Category category) {
        boolean checkAlreadyExist=categoryRepository.existsByProductCategory(category.getProductCategory());
        if(checkAlreadyExist){
            return false;
        }
        Category category1 =categoryRepository.save(category);
        if(category1==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category1;
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setProductCategory(category.getProductCategory());
            category1=categoryRepository.save(existingCategory);
        } else {
            category1=categoryRepository.save(category);
        }
        if(category1==null){
            return false;
        }
        return true;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
}
