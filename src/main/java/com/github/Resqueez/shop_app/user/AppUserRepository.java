package com.github.Resqueez.shop_app.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
//    Optional<User> findByEmail(String email);
}
