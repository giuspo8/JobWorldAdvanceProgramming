package test.JobWorld;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jobworld.model.entities.User;

class TestUser {

	private static Validator validator;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testEmail() {
		User u1 = new User("gicomohotmail.it", "user2345", null, "/resources/img/galleria23.jpg");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(u1);

		assertEquals(1, constraintViolations.size());
		assertEquals("Email not well formed", constraintViolations.iterator().next().getMessage());
		
		User u2 = new User("gicomo@hotmail.it", "user2345", null, "/resources/img/galleria23.jpg");
		constraintViolations = validator.validate(u2);

		assertEquals(0, constraintViolations.size());
	}
	
	@Test
	void testPassword() {
		User u1= new User("gicomo@hotmail.it", "  ", null, "/resources/img/galleria23.jpg");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(u1);
		assertEquals(1, constraintViolations.size());
		assertEquals("La password non può essere nulla!", constraintViolations.iterator().next().getMessage());
	}

}
