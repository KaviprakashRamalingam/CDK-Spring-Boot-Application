package com.company.cdk.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.cdk.Validator.JobApplicationValidator;
import com.company.cdk.mail.MailService;
import com.company.cdk.model.Application;
import com.company.cdk.model.ApplicationDetails;
import com.company.cdk.model.Education;
import com.company.cdk.model.Job;
import com.company.cdk.model.User;
import com.company.cdk.model.WorkExperience;
import com.company.service.AppService;
import com.company.service.JobService;

import jakarta.servlet.http.HttpSession;
import net.minidev.json.JSONObject;

@Controller
public class JobApplicationController {

	@Autowired
	MailService mailService;
	
	@Autowired
	JobApplicationValidator jobApplicationValidator;
	
	@Autowired
    private AppService appService;
	
	@Autowired
    private JobService jobService;
	
	@GetMapping("/apply_now")
	public String applyNow(Model model, HttpSession session, @RequestParam("jobId") int jobId) {  
		session.removeAttribute("job_msg");
		model.addAttribute("application", new Application());
		model.addAttribute("applicationDetails", new ApplicationDetails());
		User loggedInUser = (User) session.getAttribute("loginuser");
		boolean isApplied = appService.isApplied(loggedInUser.getEmail(), jobId);
		if (!isApplied) {
			return "apply_now";
		}else {
			return "applied";
		}	
	}

	@PostMapping("/submit_application")
	public String submitApplication(@ModelAttribute("application") Application application, BindingResult result, HttpSession session,
			@RequestParam("jobId") int jobId, @RequestParam(value = "resumeFile", required = false) MultipartFile resumeFile,
			@RequestParam("collegeName[]") String[] collegeNames, @RequestParam("startDate[]") String[] startDates,
			@RequestParam("endDate[]") String[] endDates, @RequestParam("companyName[]") String[] companyNames,
			@RequestParam("workStartDate[]") String[] w_startDates, @RequestParam("workEndDate[]") String[] w_endDates,
			@RequestParam("applicationDetails.gender") String gender, @RequestParam("applicationDetails.race") String race, 
			@RequestParam(value = "applicationDetails.authorization", required = false) String authorization, @RequestParam("applicationDetails.veteran") String usMilitaryVeteran,
			@RequestParam(value = "applicationDetails.sponsorshipQuestion", required = false) String sponsorship, @RequestParam(value = "applicationDetails.hispanic", required = false) String hispanic,
			@RequestParam(value = "applicationDetails.terms", required = false) Boolean terms) {
		
		jobApplicationValidator.validate(application, result);
		if (result.hasErrors()) {
			return "apply_now";
		}
//		
		
		if (!resumeFile.isEmpty()) {
			try {
				String uploadDirectory = "/Users/kaviprakashramalingam/SpringBoot/uploads";
				String fileName = application.getEmail() + "_" + resumeFile.getOriginalFilename();
				Path filePath = Paths.get(uploadDirectory, fileName);
				Files.copy(resumeFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				application.setResumePath(filePath.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		User loggedInUser = (User) session.getAttribute("loginuser");
		application.setUseremail(loggedInUser.getEmail());
		application.setJobid(jobId);
		Job job = jobService.getJobById(jobId);
		application.setJobtitle(job.getTitle());
		if (collegeNames != null && startDates != null && endDates != null) {
			for (int i = 0; i < collegeNames.length; i++) {
				Education education = new Education();
				education.setCollegeName(collegeNames[i]);
				education.setStartDate(startDates[i]);
				education.setEndDate(endDates[i]);
				education.setApplication(application); // Set the parent application
				application.getEducationList().add(education); // Add education to application's list
			}
		}

		if (companyNames != null && w_startDates != null && w_endDates != null) {
			for (int i = 0; i < companyNames.length; i++) {
				WorkExperience workExperience = new WorkExperience();
				workExperience.setCompanyName(companyNames[i]);
				workExperience.setWorkStartDate(w_startDates[i]);
				workExperience.setWorkEndDate(w_endDates[i]);
				workExperience.setApplication(application); // Set the parent application
				application.getWorkExperienceList().add(workExperience); // Add work experience to application's list
			}
		}
		// Populate application details
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setSponsorshipQuestion(sponsorship);
        applicationDetails.setGender(gender);
        applicationDetails.setAuthorization(authorization);
        applicationDetails.setHispanic(hispanic);
        applicationDetails.setRace(race);
        applicationDetails.setVeteran(usMilitaryVeteran);
        applicationDetails.setTerms(terms);
        applicationDetails.setApplication(application);
        // Set up bidirectional relationship
        application.setApplicationDetails(applicationDetails);

		appService.saveApplication(application);
		mailService.sendNewMail(application.getEmail(), "Thank you for applying", "Dear " + application.getFirstName()
				+ ",\n\nThank you for applying for the job. We have received your application along with the attached resume.\n\nBest regards,\nCarrer Development Kit\n",
				application.getResumePath());
		return "redirect:/welcome_user.htm";
	}

	@PostMapping("/fill_data")
	@ResponseBody
	public String fill_data(MultipartFile resume) {
		String firstName = "";
		String lastName = "";
		String email = "";
		String phoneNumber = "";

		try (InputStream inputStream = resume.getInputStream()) {
			PDDocument document = PDDocument.load(inputStream);
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			document.close();

			String[] words = text.split("\\s+");
			firstName = words[0];
			lastName = words[1];
			
			Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
	        Matcher matcher = pattern.matcher(text);
	        if (matcher.find()) {
	            phoneNumber = matcher.group();
	        }
	        
	        Pattern email_pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
	        Matcher email_matcher = email_pattern.matcher(text);
	        if (email_matcher.find()) {
	            email = email_matcher.group();
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create a JSON object to hold the extracted data
		JSONObject jsonData = new JSONObject();
		jsonData.put("firstName", firstName);
		jsonData.put("lastName", lastName);
		jsonData.put("email", email);
		jsonData.put("phoneNumber", phoneNumber);

		// Convert JSON object to string and return as response
		return jsonData.toString();
	}
	
    @GetMapping("/deleteApplication.htm")
    public String deletePost(@RequestParam("app_Id") int app_Id, HttpSession session, RedirectAttributes redirectAttributes) {
    	appService.deleteApplication(app_Id);
    	redirectAttributes.addFlashAttribute("successMessage", "Application Withdrawn successfully.");
    	return "redirect:/mydetails.htm";
    }
}
