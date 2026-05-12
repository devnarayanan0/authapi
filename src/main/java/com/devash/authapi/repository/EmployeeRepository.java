package com.devash.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devash.authapi.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findAll();
}