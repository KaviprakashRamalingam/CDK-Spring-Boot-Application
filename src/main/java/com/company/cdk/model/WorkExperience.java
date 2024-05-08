package com.company.cdk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Embeddable
@Entity
@Table(name = "work_experience")
public class WorkExperience {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "work_start_date")
    private String workStartDate;
	
	@Column(name = "work_end_date")
    private String workEndDate;
	
	@ManyToOne
    @JoinColumn(name = "application_form_id", nullable = false)
    private Application application;
    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getWorkEndDate() {
		return workEndDate;
	}
	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}
	public WorkExperience() {
	}

    
}
