package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Model.ProductModel;
import com.example.ecommerceapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/product")
@CrossOrigin(origins = "http://localhost:5173" +
        "")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/createProduct")
    public ProductModel createProduct(@RequestBody ProductModel productModel) {
        return productService.saveProduct(productModel);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
}
