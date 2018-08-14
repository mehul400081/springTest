package io.mehul.springTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import io.mehul.springTest.entity.Employee;
import io.mehul.springTest.entity.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void whenFindByFirstName_thenReturnEmployee() {
	    // given
	    Employee alex = new Employee();
	    alex.setFirstName("Alex");
	    alex.setLastName("Meijer");
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    // when
	   List<Employee> lst = employeeRepository.findByFirstName(alex.getFirstName());
	 
	    // then
	    assertThat(lst.get(0).getFirstName())
	      .isEqualTo(alex.getFirstName());
	}
}
