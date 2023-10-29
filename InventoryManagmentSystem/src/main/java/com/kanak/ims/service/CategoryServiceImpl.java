package com.kanak.ims.service;

import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
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
        Optional<Category> category=categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public void addCategory(Category category) {
            categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory=optionalCategory.get();
            existingCategory.setProductCategory(category.getProductCategory());
            categoryRepository.save(existingCategory);
        }
        else{
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
