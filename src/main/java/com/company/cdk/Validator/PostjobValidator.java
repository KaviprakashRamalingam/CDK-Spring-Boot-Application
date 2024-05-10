package com.company.cdk.Validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.company.cdk.model.Job;

@Repository
public class PostjobValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object o, Errors errors) {
		Job job = (Job) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required", "Title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "field.required", "Company is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "place", "field.required", "Place is required");

        if (job.getDescription().length() < 50 || job.getDescription().length() > 254) {
            errors.rejectValue("description", "description.short", "Description must be at least 50 and maximum of 255 characters long");
        }

        if (job.getSkills() == null || job.getSkills().isEmpty()) {
            errors.rejectValue("skills", "skills.empty", "At least one skill should be selected");
        }

        if (job.getFullDesc().length() < 100) {
            errors.rejectValue("fullDesc", "fullDesc.short", "Full Description must be at least 100 and maximum of 1200 characters long");
        }
		
	}

}
