package com.company.service.Impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.company.cdk.dao.UserDAO;
import com.company.cdk.model.User;
import com.company.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void registerUser(User user) {
		if (user.getPassword() != null) {
			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashedPassword);
		}
		userDAO.saveUser(user);
	}

	@Override
	public User loginUser(String email, String password) {
		return userDAO.loginUser(email, password);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public boolean userExists(String email) {
		return userDAO.userCheck(email);
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void processOAuth2Login(OAuth2User oAuth2User, HttpSession session) {
	    String email = oAuth2User.getAttribute("email");
	    String fullname = oAuth2User.getAttribute("name");

	    User user = getUserByEmail(email); // Using UserService method to get user by email
	    if (user == null) {
	        // If the user doesn't exist, create a new user
	        user = new User();
	        user.setEmail(email);
	        user.setFullname(fullname);
	        registerUser(user); // Using UserService method to register the new user
	    }

	    session.setAttribute("loginuser", user);
	    session.removeAttribute("emp_msg");

	    if (user.getUserType() != null) {
	        session.setAttribute("msg", "Welcome back!");
	        // Redirect to the appropriate page based on user type
	    } else {
	        session.setAttribute("msg", "Please select a role");
	        // Redirect to role selection page
	    }
	}

}
