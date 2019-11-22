package services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dao.CompanyDao;
import model.dao.CurriculumDao;
import model.entities.Company;
import model.entities.Curriculum;

@Transactional
@Service("curriculumService")
public class CurriculumServiceDefault {
private CurriculumDao curriculumRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Curriculum findById(Long id) {
		return this.curriculumRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	@Transactional
	@Override
	public Curriculum create(String firstName, String lastName) {
		return this.create(firstName, lastName, null);
	}

	@Transactional
	@Override
	public Curriculum create(String firstName, String lastName, LocalDate birthDate) {
		return this.curriculumRepository.create(firstName, lastName, birthDate);

	}
	
	@Transactional
	@Override
	public Curriculum update(Curriculum curriculum) {
		return this.curriculumRepository.update(curriculum);
	}

	@Transactional
	@Override
	public void delete(Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}


	@Autowired
	public void setCurriculumRepository(CurriculumDao curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}
}
