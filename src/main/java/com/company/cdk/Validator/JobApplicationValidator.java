package com.company.cdk.Validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.company.cdk.model.Application;

@Repository
public class JobApplicationValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return (Application.class.equals(clazz));
	}

	@Override
	public void validate(Object o, Errors errors) {
		Application application = (Application) o;
		
		if (application.getResumeFile() == null || application.getResumeFile().isEmpty()) {
            errors.rejectValue("resumeFile", "resume.required", "Resume file is required");
        } else {
            if (!application.getResumeFile().getOriginalFilename().endsWith(".pdf")) {
                errors.rejectValue("resumeFile", "resume.invalidFormat", "Resume must be a PDF file");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "First Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "Last Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Email is required");
        if (application.getEmail().isEmpty()) {
        } else {
            if (!application.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                errors.rejectValue("email", "email.invalid", "Invalid email format");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required", "Phone is required");
        if (!application.getPhone().matches("\\d{3}-\\d{3}-\\d{4}")) {
            errors.rejectValue("phone", "phone.invalidFormat", "Phone number must be in the format xxx-xxx-xxxx");
        }
        
        if (application.getApplicationDetails().getAuthorization() == null || application.getApplicationDetails().getAuthorization().isEmpty()) {
            errors.rejectValue("applicationDetails.authorization", "authorization.required", "Legal authorization is required");
        }

        if (application.getApplicationDetails().getSponsorshipQuestion() == null || application.getApplicationDetails().getSponsorshipQuestion().isEmpty()) {
            errors.rejectValue("applicationDetails.sponsorshipQuestion", "sponsorshipQuestion.required", "Sponsorship selection is required");
        }

        if (application.getApplicationDetails().getGender() == null || application.getApplicationDetails().getGender().isEmpty()) {
            errors.rejectValue("applicationDetails.gender", "gender.required", "Gender selection is required");
        }

        if (application.getApplicationDetails().getHispanic() == null || application.getApplicationDetails().getHispanic().isEmpty()) {
            errors.rejectValue("applicationDetails.hispanic", "hispanic.required", "Hispanic or Latino selection is required");
        }

        if (application.getApplicationDetails().getRace() == null || application.getApplicationDetails().getRace().isEmpty()) {
            errors.rejectValue("applicationDetails.race", "race.required", "Race selection is required");
        }

        if (application.getApplicationDetails().getVeteran() == null || application.getApplicationDetails().getVeteran().isEmpty()) {
            errors.rejectValue("applicationDetails.veteran", "veteran.required", "U.S. Military Veteran selection is required");
        }
        
        Boolean terms = application.getApplicationDetails().getTerms();
        if (terms == null || !terms) {
            errors.rejectValue("applicationDetails.terms", "terms.required", "Terms and conditions must be accepted");
        }
    }

}
