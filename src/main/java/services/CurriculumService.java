/**
 * Interfaccia CurriculumService
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
package services;

import java.util.List;

import model.entities.Curriculum;
import model.entities.Person;

public interface CurriculumService {
	List<Curriculum> findAll();
	
	Curriculum findByPersonId(Person person);
		
	Curriculum create(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo);
		
	Curriculum create(String string, String string2);
		
	Curriculum update(Curriculum curriculum);
		
		void delete(Curriculum contact);
}
