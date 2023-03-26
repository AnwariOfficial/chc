package com.afghancoders.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;

import com.afghancoders.domain.Project;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;

public interface ProjectService {

	Project register(
	 String name,
	 String duration,
	 long budget,
	 String areaOfImplemenation,
	 String location,
	 String projectId,
	 int noOfClients,
	 String link,
	 int noOfPayments) throws MessagingException, UserNotFoundException, UsernameExistException, EmailExistException;
    Project findProjectByName(String name);
    Project findProjectById(long name);
    Project findProjectByProjectId(String projectId);

    Project updateProject(long id, String name,
   		 String duration,
   		 long budget,
   		 String areaOfImplemenation,
   		 String location,
   		 String projectId,
   		 int noOfClients,
   		 String link,
   		 int noOfPayments) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteProject(Long id) throws IOException;
    Page<Project> getProjects(String name, int page, int size);


		
}
