package com.devash.authapi.service;

import com.devash.authapi.dto.EmployeeRequestDTO;
import com.devash.authapi.entity.Employee;
import com.devash.authapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    public Employee addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setOrganization(employeeRequestDTO.getOrganization());
        employeeRepository.save(employee);
        return employee;
    }
    public Employee editEmployee(EmployeeRequestDTO employeeRequestDTO, UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) return null;
        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setOrganization(employeeRequestDTO.getOrganization());
        //
        return employeeRepository.save(employee);
    }
    public void deleteById(UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeRepository.delete(employee);
    }
}
