package com.kanak.ims.service;
import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import com.kanak.ims.repository.CategoryRepository;
import com.kanak.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProductsActive() {

        return productRepository.findByStatus("active");
    }
    @Override
    public List<Product> getAllProductsInactive() {
        return productRepository.findByStatus("inactive");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public boolean addProduct(Product product) {
//      // need to check this logic
        boolean checkAlreadyExistProduct=productRepository.existsByNameAndBatchNo(product.getName(),product.getBatchNo());
        if(checkAlreadyExistProduct){
            return false;
        }
        Category category=categoryRepository.findByProductCategory(product.getCategory().getProductCategory());
        Product product1;
        if(category==null) {
            product1=productRepository.save(product);
        }else{
            product.setCategory(category);
            product1=productRepository.save(product);
        }
        if(product1==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product1;
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setBatchNo(product.getBatchNo());
            existingProduct.setPurchasePrice(product.getPurchasePrice());
            existingProduct.setSellingPrice(product.getSellingPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setManufacturingDate(product.getManufacturingDate());
            existingProduct.setExpiryDate(product.getExpiryDate());
            Category category=categoryRepository.findByProductCategory(product.getCategory().getProductCategory());
            if(category==null) {
                existingProduct.setCategory(product.getCategory());
            }else{
                existingProduct.setCategory(category);
            }

            product1=productRepository.save(existingProduct);
        }
        else {
            //addProduct(product);
            product1=productRepository.save(product);
        }
        if(product1==null){
            return false;
        }
        return true;
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
    public List<Product> findExpiredProducts() {
        return productRepository.findExpiredProducts();
    }

    @Override
    public List<Product> fetchLowStockProducts(int quantity) {
        return productRepository.findProductsLowInStock(quantity);
    }

    @Override
    public List<Product> findProductsByBatchNo(String batchNo) {
        return productRepository.findProductsByBatchNo(batchNo);
    }

//    @Override
//    public List<Product> findByCategoryType(Long id) {
//        return productRepository.findByCategoryType(id);
//    }

    @Override
    public List<Product> findByCategoryType(String id) {
        return productRepository.findByCategoryType(id);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        productRepository.deleteByCategoryId(id);
    }

    @Override
    public List<Product> productsExpiredToday() {
        return productRepository.productsExpiredToday();
    }

    @Override
    public List<Product> productsExpiredCustom(LocalDate startDate, LocalDate endDate) {
        return productRepository.productsExpiredCustom(startDate,endDate);
    }

    @Override
    public List<Product> productsExpiredYearly(int year) {
        return productRepository.productsExpiredYearly(year);
    }

    @Override
    public List<Product> searchProduct(String s) {
        return productRepository.searchProduct(s);
    }


}

