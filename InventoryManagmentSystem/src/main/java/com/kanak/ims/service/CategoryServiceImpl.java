package com.kanak.ims.service;

import com.kanak.ims.model.Category;
import com.kanak.ims.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(categoryRepository.findById(id).isPresent()){
            return categoryRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    @Override
    public void addCategory(Category category) {
            categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, Category category) {

    }

    @Override
    public void deleteCategory(Long id) {

    }
}
