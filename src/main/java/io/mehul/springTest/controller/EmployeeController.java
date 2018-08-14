package io.mehul.springTest.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.mehul.springTest.entity.Employee;
import io.mehul.springTest.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
				
		return StreamSupport.stream(employeeService.getAllEmployees().spliterator(), false)
                .collect(Collectors.toList());
	}
	
	@GetMapping("/employee/{employeeName}")
	public List<Employee> getEmployeeByFirstName(@PathVariable String firstName) {
				
		return employeeService.getEmployeeByName(firstName);
	}
}
