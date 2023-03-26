package com.afghancoders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afghancoders.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	UserProfile findUserProfileByUserId(long userId);
}
