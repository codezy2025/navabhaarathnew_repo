package com.java.coreTemplate.repository;

import com.java.coreTemplate.model.entity.Role;
import com.java.coreTemplate.model.entity.RoleName;
import com.java.coreTemplate.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName name);
}