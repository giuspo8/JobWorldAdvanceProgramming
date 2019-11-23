package jobworld.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Person;

/**
 * Implementazione dell'interfaccia PersonDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("personDao")
public class DefaultPersonDao extends DefaultDao implements PersonDao {

	@Override
	@Transactional
	public Person create(String email, String password, String description, String firstName, String secondName,
			LocalDate birthDate, String number, String interests) {
		Person person = new Person(email, password, description, firstName, secondName, birthDate, number, interests);
		this.getSession().save(person);
		return person;
	}

	@Override
	@Transactional
	public Person update(Person person) {
		Person merged = (Person) this.getSession().merge(person);
		return merged;
	}

	@Override
	@Transactional
	public void delete(Person person) {
		this.delete(person);
	}

	@Override
	@Transactional
	public Person findById(long id) {
		return getSession().find(Person.class, id);
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
