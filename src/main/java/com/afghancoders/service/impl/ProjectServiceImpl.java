package com.afghancoders.service.impl;

import static com.afghancoders.constant.UserImplConstant.NO_PROJECT_FOUND_BY_PROJECTNAME;
import static com.afghancoders.constant.UserImplConstant.PROJECT_ID_ALREADY_EXISTS;
import static com.afghancoders.constant.UserImplConstant.PROJECT_NAME_ALREADY_EXISTS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.data.domain.PageRequest.of;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.afghancoders.domain.Project;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;
import com.afghancoders.repository.ProjectRepository;
import com.afghancoders.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;

	}

	@Override
	public Project register(String name, String duration, long budget, String areaOfImplemenation, String location,
			String projectId, int noOfClients, String link, int noOfPayments)
			throws MessagingException, UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewProjectNameAndProjectId(EMPTY, name, projectId);
		Project project = new Project();
		project.setProjectId(projectId);
		project.setId(generateUserId());
		project.setName(name);
		project.setAreaOfImplemenation(areaOfImplemenation);
		project.setBudget(budget);
		project.setDuration(duration);
		project.setLocation(location);
		//String url= "https://docs.google.com/viewer?srcid="+link+"&pid=explorer&efh=false&a=v&chrome=false&embedded=true";
		project.setLink(link);
		project.setNoOfClients(noOfClients);
		project.setNoOfPayments(noOfPayments);
		projectRepository.save(project);
		// emailService.sendNewPasswordEmail(name, areaOfImplemenation, projectId);
		return project;
	}

	@Override
	public Project updateProject(long id, String name, String duration, long budget, String areaOfImplemenation,
			String location, String projectId, int noOfClients, String link, int noOfPayments)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		Project currentProject = validateNewProjectNameAndProjectId("" + id, name, projectId);
		currentProject.setName(name);
		currentProject.setAreaOfImplemenation(areaOfImplemenation);
		currentProject.setBudget(budget);
		currentProject.setDuration(duration);
		//String url= "https://docs.google.com/viewer?srcid="+link+"&pid=explorer&efh=false&a=v&chrome=false&embedded=true";
		currentProject.setLink(link);
		currentProject.setNoOfClients(noOfClients);
		currentProject.setNoOfPayments(noOfPayments);
		currentProject.setLocation(location);
		currentProject.setProjectId(projectId);
		projectRepository.save(currentProject);
		return currentProject;
	}

	@Override
    public Page<Project> getProjects(String name, int page, int size) {
    	 LOGGER.info("Fetching users for page {} of size {}", page, size);
        return projectRepository.findByNameContaining(name, of(page, size));
    }
	@Override
	public Project findProjectById(long id) {
		return projectRepository.findProjectById(id);
	}

	@Override
	public Project findProjectByProjectId(String projectId) {
		return projectRepository.findProjectByProjectId(projectId);
	}

	@Override
	public void deleteProject(Long  id) throws IOException {
		Project project = projectRepository.findProjectById(id);
		projectRepository.deleteById(project.getId());
	}

	private Long generateUserId() {
		return Long.parseLong(RandomStringUtils.randomNumeric(10));
	}

	private Project validateNewProjectNameAndProjectId(String id, String name, String projectId)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		Project userByNewProjectname = findProjectByName(name);
		Project userByNewProjectId = findProjectByProjectId(projectId);
		if (StringUtils.isNotBlank(String.valueOf(id))) {
			Project currentProject = findProjectById(Long.parseLong(id));
			if (currentProject == null) {
				throw new UserNotFoundException(NO_PROJECT_FOUND_BY_PROJECTNAME + id);
			}
			if (userByNewProjectname != null && !currentProject.getId().equals(userByNewProjectname.getId())) {
				throw new UsernameExistException(PROJECT_NAME_ALREADY_EXISTS);
			}
			if (userByNewProjectId != null && !currentProject.getId().equals(userByNewProjectId.getId())) {
				throw new EmailExistException(PROJECT_ID_ALREADY_EXISTS);
			}
			return currentProject;
		} else {
			if (userByNewProjectname != null) {
				throw new UsernameExistException(PROJECT_NAME_ALREADY_EXISTS);
			}
			if (userByNewProjectId != null) {
				throw new EmailExistException(PROJECT_ID_ALREADY_EXISTS);
			}
			return null;
		}
	}

	@Override
	public Project findProjectByName(String name) {

		return projectRepository.findProjectByName(name);
	}

	

}
