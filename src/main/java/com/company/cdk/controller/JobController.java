package com.company.cdk.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.company.cdk.Validator.PostjobValidator;
import com.company.cdk.dao.ApplicationDAO;
import com.company.cdk.dao.UserDAO;
import com.company.cdk.model.Application;
import com.company.cdk.model.Job;
import com.company.cdk.model.User;
import com.company.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JobController {

	@Autowired
	private UserDAO userdao;

	@Autowired
	private ApplicationDAO applicationdao;

	@Autowired
	private PostjobValidator postjobValidator;
	
	@Autowired
    private JobService jobService;
	private static final Logger logger = LoggerFactory.getLogger(Job.class);
	@GetMapping("/welcome_user.htm")
	public String welcomeUser(@RequestParam(name = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		int pageSize = 10;
		int totalJobs = jobService.getTotalJobs();
		if (totalJobs == 0) {
			totalJobs = 1;
		}
		int totalPages = (int) Math.ceil((double) totalJobs / pageSize);
		page = Math.min(Math.max(page, 1), totalPages);
//            List<Job> jobs = jobdao.getAllJobs();
		List<Job> jobs = jobService.getJobsByPage(page, pageSize);
		model.addAttribute("jobs", jobs);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		return "user_home";
	}

	@GetMapping("/createjob.htm")
	public String createjob(Model model, HttpSession session) {
		session.removeAttribute("job_msg");
		User loggedUser = (User) session.getAttribute("loginuser");
		if (loggedUser.getUserType().equals("user")) {
			session.setAttribute("emp_msg", "You must be a Employer to post a job.");
			return "redirect:/welcome_user.htm";
		} else {
			model.addAttribute("job", new Job());
			return "createjob";
		}
	}

	@PostMapping("/postJob.htm")
	public String createJob(@ModelAttribute Job job, BindingResult result, Model model, SessionStatus status,
			HttpSession session) {
		try {
		postjobValidator.validate(job, result);

		if (result.hasErrors()) {
			return "createjob";
		}
		User loggedInUser = (User) session.getAttribute("loginuser");
		String skills = String.join(",", job.getSkills());
		job.setSkills(skills);
		job.setPosted_by(loggedInUser.getEmail());

		jobService.saveJob(job);

		status.setComplete();

		session.setAttribute("job_msg", "Job Posted successfully !");
		} catch (Exception e) {
            logger.error("Error occurred while posting job: " + e.getMessage(), e);
            session.setAttribute("job_msg", "An error occurred while posting job. Please try again later.");
            return "redirect:/welcome_user.htm";
        }
		return "redirect:/welcome_user.htm";
	}

	@PostMapping("/searchJobs.htm")
	public String searchJobs(@RequestParam("search") String searchText, Model model, HttpSession session) {
		session.removeAttribute("job_msg");
		List<Job> searchResults = jobService.searchJobs(searchText);
		model.addAttribute("jobs", searchResults);
		return "user_home";
	}

	@GetMapping("/getJobDetails.htm")
	public String getJobDetails(@RequestParam("jobId") int jobId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loginuser");
		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);
		if (loggedInUser.getUserType().equals("user")) {
			return "job_details";
		} else {
			return "job_details_e";
		}
	}

	@GetMapping("/logout.htm")
	public String logout(HttpSession session) {
		session.removeAttribute("job_msg");
		session.removeAttribute("loginuser");
		session.removeAttribute("msg");
		return "login";
	}

	@GetMapping("/mydetails.htm")
	public String myDetails(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loginuser");
		session.removeAttribute("job_msg");
		User user = userdao.getUserByEmail(loggedInUser.getEmail());
		model.addAttribute("user", user);
		if (user.getUserType().equals("employer")) {
			List<Job> userJobPosts = jobService.getJobPostsByUser(loggedInUser.getEmail());
			model.addAttribute("userJobPosts", userJobPosts);
			session.setAttribute("userJobPosts", userJobPosts);
			return "myEmployerDetails";
		} else {
			List<Application> userApplications = applicationdao.getApplicationByEmail(loggedInUser.getEmail());
			model.addAttribute("userApplications", userApplications);
			session.setAttribute("userApplications", userApplications);
			return "myUserDetails";
		}

	}

	@GetMapping("/updatePost.htm")
	public String updatePost(@RequestParam("jobId") int jobId, Model model) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);
		return "updatePost";
	}

	@GetMapping("/deletePost.htm")
	public String deletePost(@RequestParam("jobId") int jobId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		jobService.deleteJob(jobId);
		redirectAttributes.addFlashAttribute("successMessage", "Job deleted successfully.");
		return "redirect:/mydetails.htm";
	}

	@PostMapping("/updatePost.htm")
	public String updateJob(@ModelAttribute("job") Job job) {
		jobService.updateJob(job);
		return "redirect:/mydetails.htm";
	}
}
