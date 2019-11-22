package model.dao;

import java.util.List;

/**
 * Interfaccia del Dao del curriculum.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
import model.entities.Curriculum;
import model.entities.Person;

public interface CurriculumDao {

	Curriculum create(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo);

	Curriculum update(Curriculum curriculum);

	void delete(Curriculum curriculum);

	Curriculum findByPersonId(Person person);
	
	List<Curriculum> findAll();

}
