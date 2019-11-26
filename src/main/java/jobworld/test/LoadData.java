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

			
			// Popolamento dei dati nel databasee
//Person			
			

			p1.apply(j1);
			jobOfferDao.update(j1);
			List<JobOffer> joboffers6 = jobOfferDao.filter("Marche", "Ancona", "Ancona", "Ass", null, "Laurea",
					"non rich");
			for (JobOffer j : joboffers6) {
				System.out.println(j);
			}
			
			List<JobOffer> joboffers7 = jobOfferDao.filter(null, null, null, null, null, null,null);
			for (JobOffer j : joboffers7) {
				System.out.println(j);
			}

		

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
	
	
}
