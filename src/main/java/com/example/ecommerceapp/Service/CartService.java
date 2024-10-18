package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Model.CartItem;
import com.example.ecommerceapp.Model.CartModel;
import com.example.ecommerceapp.Model.ProductModel;
import com.example.ecommerceapp.Repository.CartRepo;
import com.example.ecommerceapp.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    public CartModel createCart() {
        CartModel cart = new CartModel();
        return cartRepo.save(cart);
    }

    public CartModel getCart(Long cartId) {
        return cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public CartModel addProductToCart(Long cartId, Long productId, int quantity) {
        CartModel cart = getCart(cartId);
        ProductModel product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingCartItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItemOpt.isPresent()) {
            CartItem existingCartItem = existingCartItemOpt.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = new CartItem(product, quantity);
            cart.addItem(newCartItem);
        }

        return cartRepo.save(cart);
    }

    public CartModel removeProductFromCart(Long cartId, Long productId) {
        CartModel cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cart.removeItem(cartItemToRemove);

        return cartRepo.save(cart);
    }
}
