package test.JobWorld;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.JobOfferDao;
import jobworld.model.dao.PersonDao;
import jobworld.model.dao.RoleDao;
import jobworld.model.dao.UserDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.Person;
import jobworld.model.entities.Role;
import jobworld.model.entities.User;
import jobworld.model.entities.JobOffer.Education;
import jobworld.model.entities.Role.TypeRole;
import jobworld.test.TestConfig;

class TestJobOfferDao {

	@Test
	void createJobOffer() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			JobOfferDao jobOfferDao=ctx.getBean("jobOfferDao", JobOfferDao.class);
			CompanyDao companyDao = ctx.getBean("companyDao", CompanyDao.class);
			UserDao userDao = ctx.getBean("userDao", UserDao.class);
			PersonDao personDao=ctx.getBean("personDao",PersonDao.class);
			RoleDao roleDao = ctx.getBean(RoleDao.class);
			
			try (Session session = sf.openSession()) {
				companyDao.setSession(session);
				userDao.setSession(session);
				roleDao.setSession(session);
				jobOfferDao.setSession(session);
				personDao.setSession(session);

				session.beginTransaction();
				Role r1 = roleDao.create(TypeRole.USER);
				Role r3 = roleDao.create(TypeRole.COMPANY);
				User u3=userDao.create("esselunga@gmail.com", userDao.encryptPassword("user3"), null,"/resources/img/galleria24.jpg");
				u3.addRole(r3);
				Company c1 = new Company("Esselunga", u3);
				u3.setCompany(c1);
				c1 = companyDao.create(c1);
				User u6=userDao.create("pippo@outlook.it", userDao.encryptPassword("pa41ssword1"), null, "/resources/img/galleria8.jpg");
				u6.addRole(r1);
				Person p1 = new Person("Gianni", "Robredo", LocalDate.of(1985, 8, 9), "254744", "informatica", u6);
				u6.setPerson(p1);
				p1=personDao.create(p1);
				session.getTransaction().commit();
				
				session.beginTransaction();
				
				JobOffer j1 = new JobOffer("Campania", "Napoli", "Napoli" , "Programmatore Java",
						"Si richiedono le seguenti caratteristiche:\r\n" + 
								"· Conoscenza del linguaggio di programmazione Java\r\n" + 
								"· Capacit� di adattamento \r\n" + 
								"· Conoscensza della lingua inglese\r\n" + 
								"· Voglia di crescere e imparare\r\n" + 
								"· Capacità di lavorare in team\r\n" + 
								"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
								"· Esperienza di almeno 3 anni\r\n" + 
								"· Preferibilmente lavoratore remoto", 
								"determinato", Education.LAUREA_TRIENNALE, "3 anni",LocalDate.of(2020, 12, 25), c1);
				c1.getJobOffers().add(j1);
				j1=jobOfferDao.create(j1);
				c1=companyDao.update(c1);
				session.getTransaction().commit();
				
				session.beginTransaction();
				Long id=j1.getId();
				JobOffer j2=jobOfferDao.findbyId(id);
				assertEquals(j2.getCompany(),c1);
				//apply
				j1.getCandidancies().add(p1);
				j1=jobOfferDao.update(j1);
				p1.getCandidacies().add(j1);
				p1= personDao.update(p1);
				session.getTransaction().commit();
				
				session.beginTransaction();
				j1=jobOfferDao.findbyId(id);
				Set<Person> candidacies= j1.getCandidancies();
				Person p3=null;
				for (Person p:candidacies) {
					if (p.getId()==p1.getId())
					{p3=p;}
				}
				assertEquals(p3,p1);
				session.getTransaction().commit();
			}
		}
	}
}
