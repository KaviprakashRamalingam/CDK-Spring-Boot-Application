package com.company.cdk.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "application")
public class Application {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
    private String lastName;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "jobid")
    private int jobid;
	
	@Column(name = "job_title")
    private String jobtitle;
	
	@Column(name = "user_email")
    private String useremail;
	
	@Column(name = "resume_path")
	private String resumePath;
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<WorkExperience> workExperienceList = new ArrayList<>();
    
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private ApplicationDetails applicationDetails;
    
    @Transient
    private MultipartFile resumeFile;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}

	public List<WorkExperience> getWorkExperienceList() {
		return workExperienceList;
	}

	public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}
	
	public ApplicationDetails getApplicationDetails() {
		return applicationDetails;
	}

	public void setApplicationDetails(ApplicationDetails applicationDetails) {
		this.applicationDetails = applicationDetails;
	}

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	
	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	
	public MultipartFile getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(MultipartFile resumeFile) {
		this.resumeFile = resumeFile;
	}

	public Application() {
	}
}
