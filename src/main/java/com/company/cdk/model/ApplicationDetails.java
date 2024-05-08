package com.company.cdk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_details")
public class ApplicationDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", unique = true)
    private Application application;
    
	@Column(name = "authorization")
    private String authorization;
	
    @Column(name = "sponsorship_question")
    private String sponsorshipQuestion;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "hispanic")
    private String hispanic;
    
    @Column(name = "race")
    private String race;
    
    @Column(name = "veteran")
    private String veteran;
    
    @Column(name = "terms")
    private Boolean terms;
    
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

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getSponsorshipQuestion() {
		return sponsorshipQuestion;
	}

	public void setSponsorshipQuestion(String sponsorshipQuestion) {
		this.sponsorshipQuestion = sponsorshipQuestion;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHispanic() {
		return hispanic;
	}

	public void setHispanic(String hispanic) {
		this.hispanic = hispanic;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getVeteran() {
		return veteran;
	}

	public void setVeteran(String veteran) {
		this.veteran = veteran;
	}
	
	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "ApplicationDetails [id=" + id + ", application=" + application + ", authorization=" + authorization
				+ ", sponsorshipQuestion=" + sponsorshipQuestion + ", gender=" + gender + ", hispanic=" + hispanic
				+ ", race=" + race + ", veteran=" + veteran + ", terms=" + terms + "]";
	}

	public ApplicationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
