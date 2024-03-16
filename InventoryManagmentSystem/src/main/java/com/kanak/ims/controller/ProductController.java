package com.kanak.ims.controller;

import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import com.kanak.ims.service.CategoryServiceImpl;
import com.kanak.ims.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user/products")

public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestParam("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return new ResponseEntity<>("Product Not Found with given id : "+id,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while fetching product with id :{}",e.getMessage());
            return  new ResponseEntity<>("Error while fetching product with id",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            boolean res = productService.addProduct(product);
            if (res) {
                return new ResponseEntity<>("Product Saved successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Product already exists with given batch no",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            LOGGER.error("Error while adding Product :{}",e.getMessage());
            return new ResponseEntity<>("Error while adding Product",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestParam("id") Long id, @RequestBody Product product) {
        try {
            boolean res = productService.updateProduct(id, product);
            if (res) {
                return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Facing conflict while updating product",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            LOGGER.error("Error while updating product :{}",e.getMessage());
            return new ResponseEntity<>("Error while updating product",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
// delete feature remove from logic
//    @DeleteMapping("/deleteProduct")
//    public void deleteProduct(@RequestParam("id") Long id) {
//        productService.deleteProduct(id);
//    }

    @GetMapping("/nearExpiry")
    public ResponseEntity<?> findProductNearExpiry() {
        try {
            List<Product> productList = productService.findNearExpiryProducts();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found near expiry",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productService.findNearExpiryProducts(),HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching near expiry products :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching near expiry products",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lowStock")
    public ResponseEntity<?> findProductLowInStock(@RequestParam("qty") int qty) {
        try {
            List<Product> productList = productService.fetchLowStockProducts(qty);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found low in stocks",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productService.fetchLowStockProducts(qty),HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products low in stock :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products low in stock",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/batchNo")
    public ResponseEntity<?> findProductByBatchNo(@RequestParam("batch") String batch) {
        try {
            List<Product> productList = productService.findProductsByBatchNo(batch);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found with Given Batch No : "+batch,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with batch No :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with batch No",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byCategory")
    public ResponseEntity<?> findByCategoryType(@RequestParam("category") Long cat) {
        try {
            List<Product> productList = productService.findByCategoryType(cat);
            Category category=categoryService.getCategoryById(cat);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found with Given Category : "+category.getProductCategory(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with given Category :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with given Category",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProductsActive")
    public ResponseEntity<?> findAllProductActive() {
        try {
            List<Product> productList = productService.getAllProductsActive();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found with Status Active",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with active status :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with active status",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> findAllProduct() {
        try {
            List<Product> productList = productService.getAllProducts();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while Fetching products :{}",e.getMessage());
            return new ResponseEntity<>("Error while Fetching products",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/allProductsInactive")
    public ResponseEntity<?> findAllProductInactive() {
        try {
            List<Product> productList = productService.getAllProductsInactive();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found with inactive status",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with inactive status :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with inactive status",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byName")
    public ResponseEntity<?> findProductByName(@RequestParam("name") String name) {
        try {
            List<Product> productList = productService.findProductByName(name);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found with given name : "+name,HttpStatus.NOT_FOUND);
              //  return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products with name :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products with name",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchProduct")
    public ResponseEntity<?> searchProduct(String s) {
        try {
            List<Product> productList = productService.searchProduct(s);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No products found which starts with : "+s,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching products in search bar :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching products in search bar",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expiredProducts")
    public ResponseEntity<?> findExpiredProducts() {
        try {
            List<Product> productList = productService.findExpiredProducts();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No expired products found",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching expired products :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching expired products",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expiredProductsToday")
    public ResponseEntity<?> findExpiredProductsToday() {
        try {
            List<Product> productList = productService.productsExpiredToday();
            if (productList.size() == 0) {
                return new ResponseEntity<>("No expired products found today",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching expired products today :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching expired products today",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expiredProductsCustom")
    public ResponseEntity<?> findExpiredProductsCustom(@RequestParam("startDate") LocalDate sDate, @RequestParam("endDate") LocalDate eDate) {
        try {
            List<Product> productList = productService.productsExpiredCustom(sDate,eDate);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No expired products found between + start Date :"+sDate+" and end Date : "+eDate,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching expired products in given range :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching expired products in given range",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/expiredProductsYearly")
    public ResponseEntity<?> findExpiredProductsYearly(@RequestParam("year") int year) {
        try {
            List<Product> productList = productService.productsExpiredYearly(year);
            if (productList.size() == 0) {
                return new ResponseEntity<>("No expired products found in given year : "+year,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error Fetching expired products in given year :{}",e.getMessage());
            return new ResponseEntity<>("Error Fetching expired products in given year",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}