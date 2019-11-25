package jobworld.test;
/**
 * Classe LoadData "nuovo main"
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */

import java.time.LocalDate;
import java.util.List;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jobworld.app.DataServiceConfig;
import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.CurriculumDao;
import jobworld.model.dao.JobOfferDao;
import jobworld.model.dao.PersonDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
public class LoadData {			
	public static void main(String ...args) {
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class)) {

			CompanyDao companyDao = ctx.getBean(CompanyDao.class);
			JobOfferDao jobOfferDao = ctx.getBean(JobOfferDao.class);
			CurriculumDao curriculumDao = ctx.getBean(CurriculumDao.class);
			PersonDao personDao = ctx.getBean(PersonDao.class);

			
			// Aggiunta dei dati al database già fatto
			
			// phase 2 : navigate data in the database
			
			
			/*
			 * List<JobOffer> jobOffers = jobOfferDao.findAll(); for (JobOffer j :
			 * jobOffers) { System.out.println(j); }
			 * 
			 * List<JobOffer> jobOffers2 = jobOfferDao.findbyRegion("Abruzzo"); for
			 * (JobOffer j : jobOffers2) { System.out.println(j); }
			 * 
			 * List<JobOffer> jobOffers3 = jobOfferDao.filterByPosition("adlla"); for
			 * (JobOffer j : jobOffers3) { System.out.println(j); }
			 * 
			 * 
			 * List<JobOffer> jobOffers4 = jobOfferDao.orderedByPublicationDate(); for
			 * (JobOffer j : jobOffers4) { System.out.println(j); }
			 * 
			 * 
			 * List<JobOffer> jobOffers5 =
			 * jobOfferDao.filterBypositionAndprovince("assistente", "Ancona"); for
			 * (JobOffer j : jobOffers5) { System.out.println(j); }
			 */
			Person p1= personDao.findById(1);
			Person p2= personDao.findById(2);
			JobOffer j1=jobOfferDao.findbyId(1);
			JobOffer j2=jobOfferDao.findbyId(2);
			JobOffer j3=jobOfferDao.findbyId(3);
			Company c1=companyDao.findbyId(1);
			Company c2=companyDao.findbyId(2);
			Company c3=companyDao.findbyId(3);
			p2.apply(j2);
			jobOfferDao.update(j2);
			List<JobOffer> joboffers6 = jobOfferDao.filter("Marche", "Ancona", "Ancona", "Ass", null, "Laurea",
					"non rich");
			for (JobOffer j : joboffers6) {
				System.out.println(j);
			}
			
			List<JobOffer> joboffers7 = jobOfferDao.filter(null, null, null, null, null, null,null);
			for (JobOffer j : joboffers7) {
				System.out.println(j);
			}

			System.out.println(curriculumDao.findByPersonId(p1));

			for (JobOffer j : p1.getCandidacies()) {
				System.out.println(j);
			}
			

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
	
	
}
