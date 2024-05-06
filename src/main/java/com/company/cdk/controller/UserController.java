package com.company.cdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import com.company.cdk.Validator.RegistrationValidator;
import com.company.cdk.model.User;
import com.company.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

	@Autowired
	private RegistrationValidator registrationValidator;
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User()); // Initialize a new User object and add it to the model
		return "register";
	}

	@PostMapping("/createUser.htm")
	public String createUser(@ModelAttribute User user, BindingResult result, Model model, SessionStatus status,
	                         HttpSession session) {
	    try {
	        registrationValidator.validate(user, result);
	        if (result.hasErrors()) {
	            // Validation failed, return to the registration form with error messages
	            return "register";
	        }
	        if (!userService.userExists(user.getEmail())) {
                userService.registerUser(user);
                status.setComplete();
                session.setAttribute("msg", "Registered successfully. Please Login");
            } else {
                session.setAttribute("msg", "User already present. Please Login");
            }
	    } catch (Exception e) {
	        logger.error("Error occurred during user registration: " + e.getMessage(), e);
	        session.setAttribute("msg", "An error occurred during user registration. Please try again later.");
	    }
	    return "register";
	}

	
	@GetMapping("/useroauth2login")
	public String loginoauth2(Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
			OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
			// Extract user details from OAuth2User
			userService.processOAuth2Login(oAuth2User, session);

	        String msg = (String) session.getAttribute("msg");
	        if (msg.equals("Welcome back!")) {
	            return "redirect:/welcome_user.htm";
	        } else {
	            return "role";
	        }

		} else {
			session.setAttribute("msg", "Failed to retrieve user details from OAuth2 login");
			return "redirect:/login";
		}
	}

	@GetMapping("/selectRole")
	public String selectRole(@RequestParam("role") String role, HttpSession session) {
		User user = (User) session.getAttribute("loginuser");
        if (user.getEmail() != null) {
            if (!userService.userExists(user.getEmail())) {
                userService.registerUser(user);
            }else {
            	user.setUserType(role);
            	userService.updateUser(user);
            }
            session.setAttribute("loginuser", user);
            return "redirect:/welcome_user.htm";
        } else {
            session.setAttribute("msg", "User not logged in");
            return "redirect:/login.htm";
        }
	}

	@PostMapping("/userlogin")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
	                        HttpSession session) {
	    try {
            User user = userService.loginUser(email, password);
            if (user != null) {
                session.setAttribute("loginuser", user);
                session.removeAttribute("emp_msg");
                return "redirect:/welcome_user.htm";
            } else {
                session.setAttribute("msg", "Invalid email and password");
            }
        } catch (Exception e) {
            logger.error("Error occurred during user login: " + e.getMessage(), e);
            session.setAttribute("msg", "An error occurred during user login. Please try again later.");
        }
        return "redirect:/login";
	}

	
	@GetMapping("/updateProfile.htm")
	public String updateProfile(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("loginuser");
		model.addAttribute("user", loggedInUser);
		return "updateProfile";
	}
	
	@PostMapping("/updateUser.htm")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/mydetails.htm";
	}
}
