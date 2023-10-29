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

// addProduct using @requestBody
//    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
//    public ModelAndView addProduct(@RequestBody Product product) {
//        productService.addProduct(product);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("success");
//        return modelAndView;
//    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public ModelAndView addProduct(HttpServletRequest request) {
        String name=request.getParameter("name");
        double purchasePrice= Double.parseDouble(request.getParameter("purchasePrice"));
        double sellingPrice= Double.parseDouble(request.getParameter("sellingPrice"));
        int quantity= Integer.parseInt(request.getParameter("quantity"));
        LocalDate mdate= LocalDate.parse(request.getParameter("manufacturingDate"));
        LocalDate edate= LocalDate.parse(request.getParameter("expiryDate"));
        List<Category> lcategory=categoryService.getAllCategory();
        String cat=request.getParameter("category");
        for(Category obj :lcategory){
            if(obj.getProductCategory().equals(cat)){

            }
        }
        //category.setProductCategory(request.getParameter("category"));
        Product product=new Product();
        product.setName(name);
        product.setPurchasePrice(purchasePrice);
        product.setSellingPrice(sellingPrice);
        product.setQuantity(quantity);
        product.setManufacturingDate(mdate);
        product.setExpiryDate(edate);
        product.setCategory(categoryService.getCategoryById());
        productService.addProduct(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}