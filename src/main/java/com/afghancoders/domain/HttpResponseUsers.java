package com.afghancoders.domain;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data		
@JsonInclude(NON_DEFAULT)
public class HttpResponseUsers {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected Map<?, ?> data;
	public HttpResponseUsers(String timeStamp, int statusCode, HttpStatus status, String message, Map<?, ?> data) {
		super();
		this.timeStamp = timeStamp;
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
	public HttpResponseUsers() {
		super();
	}


	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<?, ?> getData() {
		return data;
	}
	public void setData(Map<?, ?> data) {
		this.data = data;
	}
    

	
    
}
