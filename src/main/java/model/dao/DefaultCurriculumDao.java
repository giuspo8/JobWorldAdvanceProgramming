package model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.entities.Curriculum;
import model.entities.Person;

/**
 * Classe del Dao del curriculum.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Transactional
@Repository("curriculumDao")
public class DefaultCurriculumDao extends DefaultDao implements CurriculumDao {

	@Override
	@Transactional
	public Curriculum create(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo) {
		Curriculum curriculum = new Curriculum(person, workExperience, education, personalSkills, additionalInfo);
		this.getSession().save(curriculum);
		return curriculum;
	}

	@Override
	@Transactional
	public Curriculum update(Curriculum curriculum) {
		Curriculum merged = (Curriculum) this.getSession().merge(curriculum);
		return merged;
	}

	@Override
	@Transactional
	public void delete(Curriculum curriculum) {
		this.delete(curriculum);
	}

	// data una persona ci restituisce il curriculum di quella persona
	@Override
	@Transactional
	public Curriculum findByPersonId(Person person) {
		return getSession().createQuery("from Curriculum c where c.person='" + person.getId() + "'", Curriculum.class)
				.getSingleResult();
	}

}
