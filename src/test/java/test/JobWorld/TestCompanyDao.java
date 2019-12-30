package test.JobWorld;

import static org.junit.jupiter.api.Assertions.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.RoleDao;
import jobworld.model.dao.UserDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.Role;
import jobworld.model.entities.User;
import jobworld.model.entities.Role.TypeRole;
import jobworld.test.TestConfig;

public class TestCompanyDao {

	@Test
	void testBeginCommitTransaction() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CompanyDao companyDao = ctx.getBean(CompanyDao.class);
			Session s = sf.openSession();

			assertTrue(s.isOpen());

			s.beginTransaction();

			companyDao.setSession(s);
			assertEquals(s, companyDao.getSession());
			s.getTransaction().commit();

			assertFalse(s.getTransaction().isActive());
		}

	}

	@Test
	void testBeginWithoutSpecifyingSession() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);

			CompanyDao companyDao = ctx.getBean("companyDao", CompanyDao.class);

			UserDao userDao = ctx.getBean("userDao", UserDao.class);

			Session s  = sf.openSession();
			
			assertTrue(s.isOpen());
			User u1 = userDao.create("gicomo@hotmail.it", userDao.encryptPassword("user2345"), null,
					"/resources/img/galleria23.jpg");

			companyDao.create(new Company("Esselunga srl", u1));

			Session s1 = companyDao.getSession();
			assertFalse(s1.isOpen());

			companyDao.create(new Company("Esse srl", u1));
			Session s2 = companyDao.getSession();

			assertNotEquals(s1, s2);

			userDao.create("gicomo2@hotmail.it", userDao.encryptPassword("user2345"), null,
					"/resources/img/galleria23.jpg");
			Session s3 = userDao.getSession();

			assertNotEquals(s1, s3);

		} catch (Exception e) {
			fail("Error unexpected: " + e);
		}
	}

	@Test
	void testCreateCompany() {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CompanyDao companyDao = ctx.getBean("companyDao", CompanyDao.class);
			UserDao userDao = ctx.getBean("userDao", UserDao.class);
			RoleDao roleDao = ctx.getBean(RoleDao.class);
			try (Session session = sf.openSession()) {
				companyDao.setSession(session);
				userDao.setSession(session);
				roleDao.setSession(session);

				session.beginTransaction();

				Role r3 = roleDao.create(TypeRole.COMPANY);
				User u7 = userDao.create("gamestop@email.it", userDao.encryptPassword("gamestop1"),
						"GameStop Corporation, noto semplicemente come GameStop, � un'azienda statunitense con sede nella citt� di Grapevine. � il pi� grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic",
						"/resources/img/companies/gamestop.jpg");
				u7.addRole(r3);
				Company c1 = new Company("Gamestop", u7);
				u7.setCompany(c1);
				c1 = companyDao.create(c1);
				session.getTransaction().commit();
				session.beginTransaction();
				Long idc = c1.getId();
				Company c2 = companyDao.findbyId(idc);
				String email = u7.getEmail();
				User u77 = userDao.findByEmail(email);
				assertEquals(c1, c2);
				assertEquals(u7, u77);
				session.getTransaction().commit();
			}
		}
	}

	@Test
	void testUpdateCompany() {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CompanyDao companyDao = ctx.getBean("companyDao", CompanyDao.class);
			UserDao userDao = ctx.getBean("userDao", UserDao.class);
			RoleDao roleDao = ctx.getBean(RoleDao.class);
			try (Session session = sf.openSession()) {
				companyDao.setSession(session);
				userDao.setSession(session);
				roleDao.setSession(session);

				session.beginTransaction();

				Role r3 = roleDao.create(TypeRole.COMPANY);
				User u7 = userDao.create("gamestop@email.it", userDao.encryptPassword("gamestop1"),
						"GameStop Corporation, noto semplicemente come GameStop, � un'azienda statunitense con sede nella citt� di Grapevine. � il pi� grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic",
						"/resources/img/companies/gamestop.jpg");
				u7.addRole(r3);
				Company c1 = new Company("Gamestop", u7);
				u7.setCompany(c1);
				Company c2 = companyDao.create(c1);
				session.getTransaction().commit();
				session.beginTransaction();
				c1.setName("Game");
				c1= companyDao.update(c1);
				assertEquals(c1,c2);//perchè puntano allo stesso oggetto
				Long id=c1.getId();
				Company c3=companyDao.findbyId(id);
				assertEquals(c3.getName(),"Game");
				session.getTransaction().commit();
			}
		}

	}

	@Test
	void testDeleteCompany() {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CompanyDao companyDao = ctx.getBean("companyDao", CompanyDao.class);
			UserDao userDao = ctx.getBean("userDao", UserDao.class);
			RoleDao roleDao = ctx.getBean(RoleDao.class);
			try (Session session = sf.openSession()) {
				companyDao.setSession(session);
				userDao.setSession(session);
				roleDao.setSession(session);

				session.beginTransaction();

				Role r3 = roleDao.create(TypeRole.COMPANY);
				User u7 = userDao.create("gamestop@email.it", userDao.encryptPassword("gamestop1"),
						"GameStop Corporation, noto semplicemente come GameStop, � un'azienda statunitense con sede nella citt� di Grapevine. � il pi� grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic",
						"/resources/img/companies/gamestop.jpg");
				u7.addRole(r3);
				Company c1 = new Company("Gamestop", u7);
				u7.setCompany(c1);
				c1 = companyDao.create(c1);
				session.getTransaction().commit();
				session.beginTransaction();
				Long idc = c1.getId();
				Company c2 = companyDao.findbyId(idc);
				assertNotEquals(c2,null);
				companyDao.delete(c1);
				Company c3 = companyDao.findbyId(idc);
				assertEquals(c3,null);
			}
		}
	}
}
