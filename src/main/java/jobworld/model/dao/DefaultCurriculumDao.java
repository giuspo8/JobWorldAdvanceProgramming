package jobworld.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jobworld.model.entities.Curriculum;
import jobworld.model.entities.Person;

/**
 * Implementazione dell'interfaccia CurriculumDao
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
	
	@Autowired
	PersonDao personDao;

	@Override
	@Transactional
	public Curriculum create(Curriculum curriculum) {
		Person p=curriculum.getPerson();
		p.setCurriculum(curriculum);
		this.getSession().save(curriculum);
		personDao.update(p);
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
		this.getSession().delete(curriculum);
	}

	// data una persona ci restituisce il curriculum di quella persona
	@Override
	@Transactional
	public Curriculum findByPersonId(Person person) {
		return getSession().createQuery("from Curriculum c where c.person=:persona", Curriculum.class)
				.setParameter("persona", person)
				.getSingleResult();
	}

	@Override
	public List<Curriculum> findAll() {
		return getSession().createQuery("from Curriculum c", Curriculum.class)
				.getResultList();
	}

}
