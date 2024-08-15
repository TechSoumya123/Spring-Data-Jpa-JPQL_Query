package com.developer.soumya.service;

import com.developer.soumya.model.Employee;
import com.developer.soumya.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Validated
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesByName(String employeeName) {
        return employeeRepository.findEmployeeByEmployeeName(employeeName);
    }

    public Employee findEmployeeById(Integer id) {
        return employeeRepository.findEmployeeByEmployeeId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, Employee not found"));
    }

    public List<Employee> fetchAllEmployeeById(List<Integer> ids) {
        return employeeRepository.findAllById(ids);
    }

    public List<Employee> getEmployeeByDeptAndAge(String department, int age) {
        return employeeRepository.findByDepartmentAndAgeGreaterThan(department, age);
    }

    public List<Employee> findAllEmployeeStartingWith(String name) {
        return employeeRepository.findAllByEmployeeNameStartingWith(name);
    }

    public List<Employee> findAllByEmployeeNameContaining(String name) {
        return employeeRepository.findAllByEmployeeNameContaining(name);
    }

    public List<Employee> findAllByEmployeeNameEndingWith(String name) {
        return employeeRepository.findByEmployeeNameEndingWith(name);
    }
}
