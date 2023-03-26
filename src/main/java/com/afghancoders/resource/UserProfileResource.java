package com.afghancoders.resource;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afghancoders.domain.UserProfile;
import com.afghancoders.exception.ExceptionHandling;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;
import com.afghancoders.service.UserProfileService;

@RestController
@RequestMapping(path = {"/userProfile" })
public class UserProfileResource extends ExceptionHandling {
	private UserProfileService userProfileService;

	@Autowired
	public UserProfileResource(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserProfile> register(@RequestParam("about") String about, @RequestParam("job") String job,
			@RequestParam("company") String company, @RequestParam("country") String country,
			@RequestParam("address") String address, @RequestParam("phone") String phone,
			@RequestParam("emailProfile") String emailProfile, @RequestParam("twitterProfile") String twitterProfile,
			@RequestParam("facebookProfile") String facebookProfile,
			@RequestParam("instagramProfile") String instagramProfile,
			@RequestParam("linkedinProfile") String linkedinProfile, @RequestParam("userId") String userId)
			throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
		UserProfile newUserProfile = userProfileService.register(about,job,company,country,address,phone,emailProfile,twitterProfile,facebookProfile,instagramProfile,linkedinProfile,Long.parseLong(userId));
		return new ResponseEntity<>(newUserProfile, OK);
	}

	@PostMapping("/update")
	public ResponseEntity<UserProfile> update(@RequestParam("about") String about, @RequestParam("job") String job,
			@RequestParam("company") String company, @RequestParam("country") String country,
			@RequestParam("address") String address, @RequestParam("phone") String phone,
			@RequestParam("emailProfile") String emailProfile, @RequestParam("twitterProfile") String twitterProfile,
			@RequestParam("facebookProfile") String facebookProfile,
			@RequestParam("instagramProfile") String instagramProfile,
			@RequestParam("linkedinProfile") String linkedinProfile, @RequestParam("userId") String userId)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		UserProfile updatedUserProfile = userProfileService.updateUserProfile(about, job, company, country, address,
				phone, emailProfile, twitterProfile, facebookProfile, instagramProfile, linkedinProfile, Long.parseLong(userId));
		return new ResponseEntity<>(updatedUserProfile, OK);
	}
	@GetMapping("/find/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable("userId") String userId) {
		UserProfile userProfile = userProfileService.findUserProfileByUserId(Long.parseLong(userId));
        return new ResponseEntity<>(userProfile, OK);
    }

}
