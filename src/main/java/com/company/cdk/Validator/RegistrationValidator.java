package com.company.cdk.Validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.company.cdk.model.User;

@Repository
public class RegistrationValidator implements Validator{

	@Override
	public boolean supports(Class<?> type) {
		return (User.class.equals(type));
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "field.required", "Full name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email is required");
        if (user.getEmail().isEmpty()) {
        } else {
            if (!user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                errors.rejectValue("email", "email.invalid", "Invalid email format");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password is required");
        if (user.getPassword().isEmpty()) {
        } else {
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "password.short", "Password must be at least 8 characters long");
            }
        }
	}

}
