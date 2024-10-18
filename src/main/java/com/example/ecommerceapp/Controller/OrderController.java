package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Model.OrderModel;
import com.example.ecommerceapp.Service.OrderService;
import com.example.ecommerceapp.Model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderModel> createOrder(
            @PathVariable Long cartId,
            @RequestBody OrderRequest orderRequest) {

        OrderModel order = orderService.createOrder(
                cartId,
                orderRequest.getCustomerName(),
                orderRequest.getCustomerAddress(),
                orderRequest.getCustomerPhone(),
                orderRequest.getPaymentMethod()
        );
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    // Delete an order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
