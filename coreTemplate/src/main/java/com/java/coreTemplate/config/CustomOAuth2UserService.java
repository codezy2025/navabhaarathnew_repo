package com.java.coreTemplate.config;

import com.java.coreTemplate.model.dto.UserPrincipal;
import com.java.coreTemplate.model.entity.RoleName;
import com.java.coreTemplate.model.entity.User;
import com.java.coreTemplate.repository.RoleRepository;
import com.java.coreTemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub") != null ?
                oAuth2User.getAttribute("sub") : oAuth2User.getAttribute("id");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(name);
                    newUser.setEmail(email);
                    newUser.setOauthProvider(provider);
                    newUser.setOauthId(providerId);
                    newUser.setRoles(Set.of(roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role not found."))));
                    return userRepository.save(newUser);
                });

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }
}