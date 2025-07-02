package com.java.coreTemplate.controller;


import com.java.coreTemplate.model.dto.UserPrincipal;
import com.java.coreTemplate.model.dto.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserProfile> getCurrentUser(@AuthenticationPrincipal UserPrincipal principal) {
        UserProfile profile = new UserProfile(
                principal.getId(),
                principal.getUsername(),
                principal.getEmail(),
                principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Admin access granted");
    }
}