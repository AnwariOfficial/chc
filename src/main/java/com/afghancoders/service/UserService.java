package com.afghancoders.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.afghancoders.domain.User;
import com.afghancoders.exception.domain.EmailExistException;
import com.afghancoders.exception.domain.EmailNotFoundException;
import com.afghancoders.exception.domain.NotAnImageFileException;
import com.afghancoders.exception.domain.UserNotFoundException;
import com.afghancoders.exception.domain.UsernameExistException;

public interface UserService {

    User register(String firstName, String lastName, String username, String email,String gender,String password) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    Page<User> getUsers(String name, int page, int size);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;
}
