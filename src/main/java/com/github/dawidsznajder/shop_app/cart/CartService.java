package com.github.dawidsznajder.shop_app.cart;

import com.github.dawidsznajder.shop_app.product.Product;
import com.github.dawidsznajder.shop_app.product.ProductRepository;
import com.github.dawidsznajder.shop_app.user.AppUser;
import com.github.dawidsznajder.shop_app.user.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final AppUserRepository appUserRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, AppUserRepository appUserRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.appUserRepository = appUserRepository;
        this.productRepository = productRepository;
    }

    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        boolean found = false;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user!"));
    }

    public Cart removeProductFromCart(Long userId, Long productId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }

    public Cart updateProductQuantity(Long userId, Long productId, int quantity) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                if (quantity <= 0) {
                    cart.getItems().remove(item);
                }
                else {
                    item.setQuantity(quantity);
                }
                return cartRepository.save(cart);
            }
        }
            throw new RuntimeException("Product not found in cart!");
    }
}
