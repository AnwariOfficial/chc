package com.afghancoders.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.afghancoders.domain.UserProfile;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;

public interface UserProfileService {
	
	 
	  UserProfile register(
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
			 long userId) throws MessagingException, UserNotFoundException, UsernameExistException, EmailExistException;

	UserProfile updateUserProfile(String about,
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
			 long userId) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

	UserProfile findUserProfileByUserId(long userId);	
}
