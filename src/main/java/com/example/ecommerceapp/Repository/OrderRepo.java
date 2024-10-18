package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel,Long> {
}
