package com.java.coreTemplate.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
