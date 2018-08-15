package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonServiceImpl;

//@ActiveProfiles("test")
//@SpringBootTest(classes = Application.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(PowerMockRunner.class)
@PrepareForTest({ PersonRepository.class })
public class PersonServiceTest {

	//@Autowired
	//private PersonServiceImpl personServiceImpl;
	
	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}
	
	@DataProvider // (parallel = true)
	public Object[][] dbUsers() {
		return new Object[][] {
			{ "Petro" },
			{ "Stepan" }
		};
	}

	@Test(dataProvider = "dbUsers")
	public void verifyFindByName(String name) {
	//public void verifyFindByName() {
		//String name = "Stepan";
		PersonRepository personRepositoryMock = PowerMockito.mock(PersonRepository.class);
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
		PersonServiceImpl personServiceImpl = new PersonServiceImpl(personRepositoryMock);
		//
		List<Person> actual = personServiceImpl.findByName(name);
		System.out.println("actual= " + actual.get(0).getName());
		Assert.assertEquals(actual, persons, "Error: Collection not Equals");
		//Assert.assertEquals("Error: Collection not Equals", actual, persons);
	}
}
