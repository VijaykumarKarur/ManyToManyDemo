package com.nexturn.manytomanydemo.repository;

import com.nexturn.manytomanydemo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByIsDeleted(Boolean isDeleted);
}
