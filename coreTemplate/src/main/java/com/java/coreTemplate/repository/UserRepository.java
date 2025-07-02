package com.java.coreTemplate.repository;

import com.java.coreTemplate.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  // Additional query methods if needed
  Optional<User> findByUsername(String username);
  Optional<User> findByOauthProviderAndOauthId(String provider, String providerId);
  boolean existsByEmail(String email);
}