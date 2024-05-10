package com.company.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.company.cdk.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public interface UserService {
	void registerUser(User user);
    User loginUser(String email, String password);
    User getUserByEmail(String email);
    boolean userExists(String email);
    void updateUser(User user);
    void processOAuth2Login(OAuth2User oAuth2User, HttpSession session);
}
