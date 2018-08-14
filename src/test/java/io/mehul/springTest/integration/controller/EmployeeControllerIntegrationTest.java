package io.mehul.springTest.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.mehul.springTest.SpringTestMain;
import io.mehul.springTest.entity.Employee;
import io.mehul.springTest.entity.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringTestMain.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class EmployeeControllerIntegrationTest {
	
	
	 @Autowired
	    private MockMvc mvc;
	 
	    @Autowired
	    private EmployeeRepository repository;

	    
	    @Test
	    public void givenEmployees_whenGetEmployees_thenStatus200()
	      throws Exception {
	     
	        createTestEmployee("Alex", "Ricoh");
	     
	        mvc.perform(get("/employee")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$[*].firstName", Matchers.containsInAnyOrder("Alex")));
	    }


		private void createTestEmployee(String firstName, String LastName) {
			Employee emp = new Employee();
			emp.setFirstName(firstName);
			emp.setLastName(LastName);
			repository.save(emp);
					
		}
}
