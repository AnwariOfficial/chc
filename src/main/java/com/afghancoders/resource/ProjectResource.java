package com.afghancoders.resource;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afghancoders.domain.HttpResponse;
import com.afghancoders.domain.HttpResponseUsers;
import com.afghancoders.domain.Project;
import com.afghancoders.domain.User;
import com.afghancoders.exception.ExceptionHandling;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;
import com.afghancoders.service.ProjectService;

@RestController
@RequestMapping(path = { "/project" })
public class ProjectResource extends ExceptionHandling {
	public static final String USER_DELETED_SUCCESSFULLY = "Project deleted successfully";
	private ProjectService projectService;

	@Autowired
	public ProjectResource(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping("/register")
	public ResponseEntity<Project> register(@RequestParam("name") String name,
			@RequestParam("duration") String duration, @RequestParam("budget") String budget,
			@RequestParam("areaOfImplemenation") String areaOfImplemenation, @RequestParam("location") String location,
			@RequestParam("projectId") String projectId, @RequestParam("noOfClients") String noOfClients,
			@RequestParam("link") String link, @RequestParam("noOfPayments") String noOfPayments)
			throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
		Project newProject = projectService.register(name, duration, Long.parseLong(budget), areaOfImplemenation,
				location, projectId, Integer.parseInt(noOfClients), link, Integer.parseInt(noOfPayments));
		return new ResponseEntity<>(newProject, OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Project> update(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("duration") String duration, @RequestParam("budget") String budget,
			@RequestParam("areaOfImplemenation") String areaOfImplemenation, @RequestParam("location") String location,
			@RequestParam("projectId") String projectId, @RequestParam("noOfClients") String noOfClients,
			@RequestParam("link") String link, @RequestParam("noOfPayments") String noOfPayments)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		Project updatedProject = projectService.updateProject(Long.parseLong(id), name, duration,
				Long.parseLong(budget), areaOfImplemenation, location, projectId, Integer.parseInt(noOfClients), link,
				Integer.parseInt(noOfPayments));
		return new ResponseEntity<>(updatedProject, OK);
	}

	@GetMapping("/find/{name}")
	public ResponseEntity<Project> getProject(@PathVariable("name") String name) {
		Project project = projectService.findProjectByName(name);
		return new ResponseEntity<>(project, OK);
	}

    @GetMapping("/list")
    public ResponseEntity<HttpResponseUsers>getUsers(@RequestParam Optional<String> name,
                                                @RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size) throws InterruptedException {
    	//TimeUnit.SECONDS.sleep(3);
       
    	HttpResponseUsers hru = new HttpResponseUsers();
    	hru.setStatus(OK);
    	hru.setStatusCode(OK.value());
    	hru.setTimeStamp(now().toString());
    	//hru.setData(of("page", projectService.getProjects(name.orElse(""), page.orElse(0), size.orElse(2))));
    	Map<String, Page<Project>> pageProject = new HashMap<>();
    	pageProject.put("page", projectService.getProjects(name.orElse(""), page.orElse(0), size.orElse(10)));
    	hru.setData(pageProject);
    	return ResponseEntity.ok().body(hru);


    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpResponse> deleteProject(@PathVariable("id") String id) throws IOException {
		projectService.deleteProject(Long.parseLong(id));
		return response(OK, USER_DELETED_SUCCESSFULLY);
	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(
				new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message),
				httpStatus);
	}
}
