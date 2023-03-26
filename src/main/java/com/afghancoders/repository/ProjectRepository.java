package com.afghancoders.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.afghancoders.domain.Project;

public interface ProjectRepository extends  PagingAndSortingRepository<Project, Long> {
	Page<Project> findByNameContaining(String name, Pageable pageable);
    Project findProjectByName(String name);
    Project findProjectById(Long name);
    Project findProjectByProjectId(String projectId);
}
