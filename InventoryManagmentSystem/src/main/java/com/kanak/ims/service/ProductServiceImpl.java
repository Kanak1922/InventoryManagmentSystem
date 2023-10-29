package com.kanak.ims.service;
import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import com.kanak.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setBatchNo(product.getBatchNo());
            existingProduct.setPurchasePrice(product.getPurchasePrice());
            existingProduct.setSellingPrice(product.getSellingPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setManufacturingDate(product.getManufacturingDate());
            existingProduct.setExpiryDate(product.getExpiryDate());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
        }
        else {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findNearExpiryProducts() {
        return productRepository.findProductsNearExpiry();
    }

    @Override
    public List<Product> fetchLowStockProducts(int quantity) {
        return productRepository.findProductsLowInStock(quantity);
    }

    @Override
    public List<Product> findProductsByBatchNo(String batchNo) {
        return productRepository.findProductsByBatchNo(batchNo);
    }

    @Override
    public List<Product> findByCategoryType(Category category) {
        return productRepository.findByCategoryType(category);
    }


}

