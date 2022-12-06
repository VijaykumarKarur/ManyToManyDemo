package com.nexturn.manytomanydemo.repository;

import com.nexturn.manytomanydemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByIsDeleted(Boolean isDeleted);
}
