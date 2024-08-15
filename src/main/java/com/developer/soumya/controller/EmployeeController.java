package com.developer.soumya.controller;

import com.developer.soumya.model.Employee;
import com.developer.soumya.repository.EmployeeRepository;
import com.developer.soumya.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/saveAll")
    public List<Employee> addAllEmployee(@RequestBody List<Employee> employees) {
        return employeeService.saveAllEmployees(employees);
    }

    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employeeWithName")
    public List<Employee> getAllEmployeesWithName(@RequestParam String employeeName) {
        return employeeService.findEmployeesByName(employeeName);
    }

    @GetMapping("/employeeById")
    public ResponseEntity<?> getEmployeeById(@RequestParam("empId") Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployeeById(id));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
        }
    }

    @GetMapping("/allById")
    public List<Employee> findAllEmployeeByIds(@RequestBody List<Integer> ids) {
        return employeeService.fetchAllEmployeeById(ids);
    }

    @GetMapping("/getEmployeeByDeptAndAge")
    public List<Employee> getEmployeeByDeptAndAge(@RequestParam String department, @RequestParam int age) {
        return employeeService.getEmployeeByDeptAndAge(department, age);
    }

    @GetMapping(path = {"/employeeName-startingWith"})
    public List<Employee> getEmployeeByNameStartingWith(@RequestParam String employeeName) {
        return employeeService.findAllEmployeeStartingWith(employeeName);
    }

    @GetMapping(path = {"/employeeName-containing"})
    public List<Employee> getEmployeeByNameContaining(@RequestParam String employeeName) {
        return employeeService.findAllByEmployeeNameContaining(employeeName);
    }

    @GetMapping(path = {"/employeeName-endingWith"})
    public List<Employee> getEmployeeNameEndingWith(@RequestParam String employeeName) {
        return employeeService.findAllByEmployeeNameEndingWith(employeeName);
    }

    @GetMapping(path = {"/employee-all-but-not-requesting"})
    public List<Employee> getEmployeesWithOutWhichIsPassing(@RequestParam String name) {
        return employeeRepository.findEmployeeByEmployeeNameIsNot(name);
    }

}
