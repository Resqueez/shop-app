package com.github.dawidsznajder.shop_app.cart;

import com.github.dawidsznajder.shop_app.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(AppUser user);
}
