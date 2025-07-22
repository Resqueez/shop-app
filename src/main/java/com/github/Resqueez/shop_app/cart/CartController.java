package com.github.Resqueez.shop_app.cart;

import com.github.Resqueez.shop_app.cart.dto.AddToCartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
   private final CartService cartService;

   public CartController(CartService cartService) {
      this.cartService = cartService;
   }

   @PostMapping("/add")
   public ResponseEntity<?> addProductToCart(@RequestBody AddToCartRequest request) {
      try {
         Cart cart = cartService.addProductToCart(
           request.getUserId(),
           request.getProductId(),
           request.getQuantity()
         );
         return ResponseEntity.ok(cart);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body("Błąd " + e.getMessage());
      }
   }

   @GetMapping("/{userId}")
   public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
      try {
         Cart cart = cartService.getCartByUserId(userId);
         return ResponseEntity.ok(cart);
      } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
      }
   }

   @DeleteMapping("/{userId}/remove/{productId}")
   public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
      try {
         Cart updatedCart = cartService.removeProductFromCart(userId, productId);
         return ResponseEntity.ok(updatedCart);
      } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
      }
   }
}
