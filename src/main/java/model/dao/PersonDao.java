package model.dao;

import java.time.LocalDate;

import model.entities.Person;

/**
 * Interfaccia del Dao dell'utente fisico.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
public interface PersonDao {

	Person create(String email, String password, String description, String firstName, String secondName,
			LocalDate birthDate, String number, String interests);

	Person update(Person person);

	void delete(Person person);

	Person findById(long id);

}
