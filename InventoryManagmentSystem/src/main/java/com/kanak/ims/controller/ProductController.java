package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/home")
    public ModelAndView getProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("success");
//        return modelAndView;
    }


    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/nearExpiry")
    public List<Product> findProductNearExpiry(){
        return productService.findNearExpiryProducts();
    }
    @GetMapping("/lowStock/{qty}")
    public List<Product> findProductLowInStock(@PathVariable int qty){
        return productService.fetchLowStockProducts(qty);
    }

    @GetMapping("/batchNo/{batch}")
    public List<Product> findProductByBatchNo(@PathVariable String batch){
        return productService.findProductsByBatchNo(batch);
    }

    @GetMapping("/byCategory/{cat}")
    public List<Product> findByCategoryType(@PathVariable Long cat){
        return productService.findByCategoryType(cat);
    }

}