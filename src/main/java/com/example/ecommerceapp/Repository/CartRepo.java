package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartModel,Long> {

}
