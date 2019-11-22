package services;

import java.time.LocalDate;
import java.util.List;

import model.entities.Curriculum;

public interface CurriculumService {
	List<Curriculum> findAll();
	
	Curriculum findById(Long id);
		
	Curriculum create(String string, String string2);
		
	Curriculum create(String string, String string2, LocalDate birthdate);
		
	Curriculum update(Curriculum curriculum);
		
		void delete(Curriculum contact);
}
