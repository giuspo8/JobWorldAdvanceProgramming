package services;

import java.time.LocalDate;
import java.util.List;


import model.entities.JobOffer;

public interface JobOfferService {
	List<JobOffer> findAll();
	
	JobOffer findById(Long id);
		
	JobOffer create(String string, String string2);
		
	JobOffer create(String string, String string2, LocalDate birthdate);
		
	JobOffer update(JobOffer joboffer);
		
		void delete(JobOffer contact);
}
