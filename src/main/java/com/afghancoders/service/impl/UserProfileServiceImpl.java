package com.afghancoders.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afghancoders.domain.User;
import com.afghancoders.domain.UserProfile;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;
import com.afghancoders.repository.UserProfileRepository;
import com.afghancoders.repository.UserRepository;
import com.afghancoders.service.UserProfileService;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {


	private UserRepository usersRepository;
	
	private UserProfileRepository userProfileRepository;
	

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository,UserRepository usersRepository) {
		this.userProfileRepository = userProfileRepository;
		this.usersRepository = usersRepository;

	}

	@Override
	public UserProfile register(String about,
			 String job,
			 String company,
			 String country,
			 String address,
			 String phone,
			 String emailProfile,
			 String twitterProfile,
			 String facebookProfile,
			 String instagramProfile,
			 String LinkedinProfile,
			 long userId)
			throws MessagingException, UserNotFoundException, UsernameExistException, EmailExistException {
		UserProfile userProfile = new UserProfile();
		
		userProfile.setAbout(about);
		userProfile.setAddress(address);
		userProfile.setCompany(company);
		userProfile.setCountry(country);
		userProfile.setEmailProfile(emailProfile);
		userProfile.setJob(job);
		userProfile.setPhone(phone);
		userProfile.setFacebookProfile(facebookProfile);
		userProfile.setInstagramProfile(instagramProfile);
		userProfile.setTwitterProfile(twitterProfile);
		userProfile.setLinkedinProfile(LinkedinProfile);
		User findUser = usersRepository.findUserById(userId);
		userProfile.setUser(findUser);
		userProfileRepository.save(userProfile);
		return userProfile;
	}

	@Override
	public UserProfile updateUserProfile(
			String about,
			 String job,
			 String company,
			 String country,
			 String address,
			 String phone,
			 String emailProfile,
			 String twitterProfile,
			 String facebookProfile,
			 String instagramProfile,
			 String LinkedinProfile,
			 long userId)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		
		UserProfile currentUserProfile = userProfileRepository.findUserProfileByUserId(userId);
		currentUserProfile.setAbout(about);
		currentUserProfile.setAddress(address);
		currentUserProfile.setCompany(company);
		currentUserProfile.setCountry(country);
		currentUserProfile.setEmailProfile(emailProfile);
		currentUserProfile.setJob(job);
		currentUserProfile.setPhone(phone);
		currentUserProfile.setFacebookProfile(facebookProfile);
		currentUserProfile.setInstagramProfile(instagramProfile);
		currentUserProfile.setTwitterProfile(twitterProfile);
		currentUserProfile.setLinkedinProfile(LinkedinProfile);
		userProfileRepository.save(currentUserProfile);
		return currentUserProfile;
	}

	@Override
	public UserProfile findUserProfileByUserId(long id) {
		User user = usersRepository.findUserById(id);
		return userProfileRepository.findUserProfileByUserId(user.getId());
	}

	

}
