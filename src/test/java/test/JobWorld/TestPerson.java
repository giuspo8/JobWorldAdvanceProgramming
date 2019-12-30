package test.JobWorld;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jobworld.model.entities.Person;
import jobworld.model.entities.User;

class TestPerson {

	private static Validator validator;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void testDate() {
		User u5=new User("luca@gmail.it", "passwo42rd1", null, 
				"/resources/img/galleria9.jpg");
		Person p1=new Person("Marco", "vitale", LocalDate.of(2020, 2, 6), 
				"3387675899", "informatica, ingegneria", u5);
		
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(p1);

		assertEquals(1, constraintViolations.size());
		assertEquals("la data di nascita deve essere antecedente a quella attuale!", constraintViolations.iterator().next().getMessage());

	}

}
