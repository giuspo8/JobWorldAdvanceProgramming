package jobworld.model.dao;
/**
 * Interfaccia di CurriculumDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
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
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.Person;

public interface CurriculumDao {

	Curriculum create(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo);

	Curriculum update(Curriculum curriculum);

	void delete(Curriculum curriculum);

	Curriculum findByPersonId(Person person);
	
	List<Curriculum> findAll();

}
