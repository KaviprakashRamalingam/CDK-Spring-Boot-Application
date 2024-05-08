package com.company.cdk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_posts")
public class Job {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobID")
	private int id;
	
	@Column(name = "title")
    private String title;
    
    @Column(name = "company")
    private String company;
    
    @Column(name = "place")
    private String place;
    
    @Column(name = "pay")
    private String pay;
    
    @Column(name = "mode")
    private String mode;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "skills")
    private String skills;
    
    @Column(name = "posted_by")
    private String posted_by;
    
    @Column(name = "education")
    private String education;
    
    @Column(name = "full_address")
    private String fullAddress;
    
    @Column(name = "full_desc", length = 12500)
    private String fullDesc;
    
    
	public Job() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getPosted_by() {
		return posted_by;
	}
	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getFullDesc() {
		return fullDesc;
	}
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", company=" + company + ", place=" + place + ", pay=" + pay
				+ ", mode=" + mode + ", description=" + description + ", skills=" + skills + ", posted_by=" + posted_by
				+ ", education=" + education + ", fullAddress=" + fullAddress + ", fullDesc=" + fullDesc + "]";
	}
    
    
	
}
