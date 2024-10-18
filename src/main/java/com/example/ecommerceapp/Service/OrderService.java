package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Model.CartItem;
import com.example.ecommerceapp.Model.CartModel;
import com.example.ecommerceapp.Model.OrderModel;
import com.example.ecommerceapp.Repository.CartRepo;
import com.example.ecommerceapp.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;

    public OrderModel createOrder(Long cartId, String customerName, String customerAddress, String customerPhone, String paymentMethod) {
        CartModel cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        Set<CartItem> orderItems = new HashSet<>(cart.getItems());

        OrderModel order = new OrderModel(orderItems, new Date(), "PENDING", customerName, customerAddress, customerPhone, paymentMethod);

        cart.getItems().clear();  // Clear the cart items after order creation

        return orderRepo.save(order);
    }

    public OrderModel getOrder(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public void updateOrderStatus(Long orderId, String status) {
        OrderModel order = getOrder(orderId);
        order.setStatus(status);
        orderRepo.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }
    public List<OrderModel> getAllOrders() {
        return orderRepo.findAll();
    }

}
