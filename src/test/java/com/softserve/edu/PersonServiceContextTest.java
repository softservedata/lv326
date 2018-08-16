package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonServiceImpl;

//@ActiveProfiles("test")
//@SpringBootTest(classes = Application.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(PowerMockRunner.class)
@TestExecutionListeners(MockitoTestExecutionListener.class)
//@DataJpaTest
@SpringBootTest
@PrepareForTest({ PersonRepository.class })
public class PersonServiceContextTest
		extends AbstractTestNGSpringContextTests {

	@Mock
	private PersonRepository personRepositoryMock;
	
	//@Autowired
	@InjectMocks
	private PersonServiceImpl personServiceImpl;
	
	@BeforeMethod
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	//@ObjectFactory
	//public IObjectFactory setObjectFactory() {
	//	return new PowerMockObjectFactory();
	//}
	
	@DataProvider // (parallel = true)
	public Object[][] dbUsers() {
		return new Object[][] {
			//{ "Petro" },
			{ "Stepan" }
		};
	}

	@Test(dataProvider = "dbUsers")
	public void verifyFindByName(String name) {
	//public void verifyFindByName() {
		//String name = "Stepan";
		//PersonRepository personRepositoryMock = PowerMockito.mock(PersonRepository.class);
		//
		List<Person> persons = new ArrayList<>();
		Person person = new Person();
		person.setPid(10);
		person.setName("Oleg");
		person.setCity("Odesa");
		persons.add(person);
		//
		PowerMockito.when(personRepositoryMock.findByName(name))
				 .thenReturn(persons);
		//PersonServiceImpl personServiceImpl = new PersonServiceImpl(personRepositoryMock);
		//
		List<Person> actual = personServiceImpl.findByName(name);
		System.out.println("actual= " + actual.get(0).getName());
		Assert.assertEquals(actual, persons, "Error: Collection not Equals");
		//Assert.assertEquals("Error: Collection not Equals", actual, persons);
	}
}
