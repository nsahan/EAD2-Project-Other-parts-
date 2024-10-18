package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Model.ProductModel;
import com.example.ecommerceapp.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductModel> getAllProducts() {
        return productRepo.findAll();
    }

    public ProductModel saveProduct(ProductModel productModel) {
        return productRepo.save(productModel);
    }

    public String deleteProduct(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted successfully.";
        } else {
            return "Product not found.";
        }
    }
}
