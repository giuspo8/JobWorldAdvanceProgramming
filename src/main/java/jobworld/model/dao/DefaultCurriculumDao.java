package jobworld.model.dao;

import java.util.List;

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
	
	/**
	 * Metodo che sovrascrive il create del curriculum
	 * @param curriculum  è il curriculum
	 * 
	 * @return curriculum
	 */
	@Override
	@Transactional
	public Curriculum create(Curriculum curriculum) {
		this.getSession().save(curriculum);
		return curriculum;
	}
	
	/**
	 * Metodo che sovrascrive l'update del curriculum
	 * @param curriculum  è il curriculum
	 * 
	 * @return merged
	 */
	@Override
	@Transactional
	public Curriculum update(Curriculum curriculum) {
		Curriculum merged = (Curriculum) this.getSession().merge(curriculum);
		return merged;
	}
	/**
	 * Metodo che sovrascrive la delete del curriculum
	 * @param curriculum  è il curriculum
	 * 
	 * @return curriculum
	 */
	@Override
	@Transactional
	public void delete(Curriculum curriculum) {
		this.getSession().delete(curriculum);
	}

	/**
	 * Metodo che sovrascrive findByPersonId del curriculum, data una persona restituisce il suo curriculum
	 * @param person  la persona
	 * 
	 * @return curriculum
	 */
	@Override
	@Transactional
	public Curriculum findByPersonId(Person person) {
		return getSession().createQuery("from Curriculum c where c.person=:persona", Curriculum.class)
				.setParameter("persona", person)
				.getSingleResult();
	}
	/**
	 * Metodo che sovrascrive il findAll del curriculum, restituisce tutti i curriculum
	 * 
	 * 
	 * @return tutti i curriculum
	 */
	@Override
	public List<Curriculum> findAll() {
		return getSession().createQuery("from Curriculum c", Curriculum.class)
				.getResultList();
	}

}
