package com.afghancoders.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.afghancoders.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Page<User> findByEmailContaining(String name, Pageable pageable);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User findUserByUserId(String UserId);
    User findUserById(long id);
}
