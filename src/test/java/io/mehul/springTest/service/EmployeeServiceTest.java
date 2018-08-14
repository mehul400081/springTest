package io.mehul.springTest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import io.mehul.springTest.entity.Employee;
import io.mehul.springTest.entity.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	@TestConfiguration
	static class EmployeeServiceTestConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeService();
		}

	}

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		Employee alex = new Employee();
		alex.setFirstName("Alex");
		alex.setLastName("Ricoh");

		List<Employee> empLst = new ArrayList<Employee>();
		empLst.add(alex);
		Mockito.when(employeeRepository.findByFirstName(alex.getFirstName()))
				.thenReturn(empLst);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String name = "Alex";
		List<Employee> found = employeeService.getEmployeeByName(name);
		assertThat(found.get(0).getFirstName()).isEqualTo(name);
	}

}
