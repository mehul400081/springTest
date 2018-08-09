package io.mehul.springTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.mehul.springTest.entity.Employee;
import io.mehul.springTest.entity.EmployeeRepository;

@Component
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Iterable<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	public List<Employee> getEmployeeByName(String firstName){
		return empRepo.findByFirstName(firstName);
	}

}
