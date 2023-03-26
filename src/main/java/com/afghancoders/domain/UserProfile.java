package com.afghancoders.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Long id;
	private String about;
	private String job;
	private String company;
	private String country;
	private String address;
	private String phone;
	private String emailProfile;
	private String twitterProfile;
	private String facebookProfile;
	private String instagramProfile;
	private String linkedinProfile;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public UserProfile(Long id, String about, String job, String company, String country, String address, String phone,
			String emailProfile, String twitterProfile, String facebookProfile, String instagramProfile,
			String linkedinProfile, User user) {
		super();
		this.id = id;
		this.about = about;
		this.job = job;
		this.company = company;
		this.country = country;
		this.address = address;
		this.phone = phone;
		this.emailProfile = emailProfile;
		this.twitterProfile = twitterProfile;
		this.facebookProfile = facebookProfile;
		this.instagramProfile = instagramProfile;
		this.linkedinProfile = linkedinProfile;
		this.user = user;
	}



	public UserProfile() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getAbout() {
		return about;
	}



	public void setAbout(String about) {
		this.about = about;
	}



	public String getJob() {
		return job;
	}



	public void setJob(String job) {
		this.job = job;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmailProfile() {
		return emailProfile;
	}



	public void setEmailProfile(String emailProfile) {
		this.emailProfile = emailProfile;
	}



	public String getTwitterProfile() {
		return twitterProfile;
	}



	public void setTwitterProfile(String twitterProfile) {
		this.twitterProfile = twitterProfile;
	}



	public String getFacebookProfile() {
		return facebookProfile;
	}



	public void setFacebookProfile(String facebookProfile) {
		this.facebookProfile = facebookProfile;
	}



	public String getInstagramProfile() {
		return instagramProfile;
	}



	public void setInstagramProfile(String instagramProfile) {
		this.instagramProfile = instagramProfile;
	}



	public String getLinkedinProfile() {
		return linkedinProfile;
	}



	public void setLinkedinProfile(String linkedinProfile) {
		this.linkedinProfile = linkedinProfile;
	}

	
	

}
