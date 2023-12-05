package com.kanak.ims.controller;

import com.kanak.ims.model.Product;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/home")
    public ModelAndView getProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam("id") Long id) {
        return productService.getProductById(id);
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }


    @PutMapping("/updateProduct")
    public void updateProduct(@RequestParam("id") Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/nearExpiry")
    public ResponseEntity<?> findProductNearExpiry() {
        return ResponseEntity.ok(productService.findNearExpiryProducts());
    }

    @GetMapping("/lowStock")
    public ResponseEntity<?> findProductLowInStock(@RequestParam("qty") int qty) {
        return ResponseEntity.ok(productService.fetchLowStockProducts(qty));
    }

    @GetMapping("/batchNo")
    public ResponseEntity<?> findProductByBatchNo(@RequestParam("batch") String batch) {
        return ResponseEntity.ok(productService.findProductsByBatchNo(batch));
    }

    @GetMapping("/byCategory")
    public ResponseEntity<?> findByCategoryType(@RequestParam("category") Long cat) {
        return ResponseEntity.ok(productService.findByCategoryType(cat));
    }

    @GetMapping("/allProductsActive")
    public ResponseEntity<?> findAllProductActive() {
        return ResponseEntity.ok(productService.getAllProductsActive());
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> findAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/allProductsInactive")
    public ResponseEntity<?> findAllProductInactive() {
        return ResponseEntity.ok(productService.getAllProductsInactive());
    }

    @GetMapping("/byName")
    public ResponseEntity<?> findProductByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productService.findProductByName(name));
    }
    @GetMapping("/searchProduct")
    public List<Product> searchProduct(String s) {
        return productService.searchProduct(s);
    }

    @GetMapping("/expiredProducts")
    public ResponseEntity<?> findExpiredProducts() {
        return ResponseEntity.ok(productService.findExpiredProducts());
    }

    @GetMapping("/expiredProductsToday")
    public ResponseEntity<?> findExpiredProductsToday() {
        return ResponseEntity.ok(productService.productsExpiredToday());
    }

    @GetMapping("/expiredProductsCustom")
    public ResponseEntity<?> findExpiredProductsCustom(@RequestParam("startDate") LocalDate sDate, @RequestParam("endDate") LocalDate eDate) {
        return ResponseEntity.ok(productService.productsExpiredCustom(sDate,eDate));
    }
    @GetMapping("/expiredProductsYearly")
    public ResponseEntity<?> findExpiredProductsYearly(@RequestParam("year") int year) {
        return ResponseEntity.ok(productService.productsExpiredYearly(year));
    }
}