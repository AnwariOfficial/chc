package com.afghancoders.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {

	@Id
	@Column(updatable = false, nullable = false)
	private Long id;
	private String name;
	private String duration;
	private Long budget;
	private String areaOfImplemenation;
	private String location;
	private String projectId;
	private int noOfClients;
	private String link;
	private int noOfPayments;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public long getBudget() {
		return budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	public String getAreaOfImplemenation() {
		return areaOfImplemenation;
	}
	public void setAreaOfImplemenation(String areaOfImplemenation) {
		this.areaOfImplemenation = areaOfImplemenation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public int getNoOfClients() {
		return noOfClients;
	}
	public void setNoOfClients(int noOfClients) {
		this.noOfClients = noOfClients;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getNoOfPayments() {
		return noOfPayments;
	}
	public void setNoOfPayments(int noOfPayments) {
		this.noOfPayments = noOfPayments;
	}
	
}
