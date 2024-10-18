package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Model.CartModel;
import com.example.ecommerceapp.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartModel> getCart(@PathVariable Long cartId) {
        CartModel cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartModel> addProductToCart(@PathVariable Long cartId,
                                                      @PathVariable Long productId,
                                                      @RequestBody Map<String, Integer> body) {
        int quantity = body.getOrDefault("quantity", 1);
        CartModel updatedCart = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping
    public ResponseEntity<CartModel> createCart() {
        CartModel newCart = cartService.createCart();
        return ResponseEntity.ok(newCart);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartId,
                                                   @PathVariable Long productId) {
        try {
            CartModel updatedCart = cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            System.err.println("Error removing product from cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }
}
