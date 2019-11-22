package test;


import java.util.List;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import app.DataServiceConfig;
import model.dao.CompanyDao;
import model.dao.CurriculumDao;
import model.dao.JobOfferDao;
import model.dao.PersonDao;
import model.entities.Person;

//PER FARE QUESTO DOVETE PRIMA FARE IL DATASERVICECONFIG

public class LoadData {
	
			
	public static void main(String ...args) {
		
		
		
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class)) {

			CompanyDao companyDao = ctx.getBean(CompanyDao.class);
			CurriculumDao curriculumDao = ctx.getBean(CurriculumDao.class);
			JobOfferDao jobofferDao = ctx.getBean(JobOfferDao.class);
			PersonDao personDao = ctx.getBean(PersonDao.class);
			// phase 1 : add data to database
	
	
			CompanyDao.create("email1", "password1", "descrizione1");
			CompanyDao.create("email2", "password2", "descrizione2");
			CompanyDao.create("email3", "password3", "descrizione3");
			
			/*
			Singer rw = singerDao.create("Roger", "Waters");
			Singer mj = singerDao.create("Michael", "Jackson", null);
			
			albumDao.create("Wish you where here", rw);
			
			albumDao.create("Thriller", mj);
			
			Album tdb = albumDao.create("The division bell", rw);
			tdb.setSinger(rw);
			albumDao.update(tdb);
			
		
			Instrument i1 = instrumentDao.findByName("Stratocaster");
			Instrument i2 = instrumentDao.findByName("Moog");
			Instrument i3 = instrumentDao.findByName("Stradivari");
			
			rw.addInstrument(i1);
			rw.addInstrument(i2);
			singerDao.update(rw);
			
			mj.addInstrument(i2);
			mj.addInstrument(i3);
			singerDao.update(mj);
		
			// phase 2 : navigate data in the database
		
			List<Singer> all = singerDao.findAll();
			
			System.out.println("Number of singers: " + all.size());
			for (Singer s : all) {
				System.out.println(" - " + s.getFullName() + " : " + s.getBirthDate());
				
				Set<Album> albums = singerDao.getAlbums(s);
				System.out.println("Number of albums: " + albums.size());
				for (Album a : albums) {
					System.out.println("  - " + a.getTitle());					
				}
			}
			*/
			List<Person> allPerson = PersonDao.findAll();
			System.out.println("Number of Person: " + allPerson.size());
			for (Person i : allPerson) {
				System.out.println( " : " + i.getFirstName());
				
				
			}
			

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
	
	
	
}
