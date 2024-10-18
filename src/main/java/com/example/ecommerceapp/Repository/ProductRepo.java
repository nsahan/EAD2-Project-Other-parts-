package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductModel, Long> {

}
