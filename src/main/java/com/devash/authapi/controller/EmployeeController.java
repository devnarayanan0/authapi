package com.devash.authapi.controller;

import com.devash.authapi.dto.EmployeeRequestDTO;
import com.devash.authapi.entity.Employee;
import com.devash.authapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeRequestDTO));
    }
}
