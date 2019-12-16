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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import jobworld.model.entities.Role;
import jobworld.model.entities.Role.TypeRole;
import jobworld.model.dao.CompanyDao;
import jobworld.model.dao.CurriculumDao;
import jobworld.model.dao.JobOfferDao;
import jobworld.model.dao.PersonDao;
import jobworld.model.dao.RoleDao;
import jobworld.model.dao.UserDao;
import jobworld.model.entities.Company;
import jobworld.model.entities.Curriculum;
import jobworld.model.entities.JobOffer;
import jobworld.model.entities.JobOffer.Education;
import jobworld.model.entities.Person;
import jobworld.model.entities.User;

public class LoadData {			
	public static void main(String ...args) {
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class)) {
			
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CompanyDao companyDao = ctx.getBean(CompanyDao.class);
			JobOfferDao jobOfferDao = ctx.getBean(JobOfferDao.class);
			CurriculumDao curriculumDao = ctx.getBean(CurriculumDao.class);
			PersonDao personDao = ctx.getBean(PersonDao.class);
			UserDao userDao=ctx.getBean(UserDao.class);
			RoleDao roleDao = ctx.getBean(RoleDao.class);
			
			try(Session session=sf.openSession()){
				companyDao.setSession(session);
				jobOfferDao.setSession(session);
				curriculumDao.setSession(session);
				personDao.setSession(session);
				userDao.setSession(session);
				roleDao.setSession(session);
				
				
				session.beginTransaction();
				
			
			// Popolamento dei dati nel database 
			
			
			
			
			
			
			//parte cifratura, va messo in ordine
			Role r1 = roleDao.create(TypeRole.USER);
			Role r2 = roleDao.create(TypeRole.ADMIN);
			User u1 = userDao.create("saviofeng@gmail.it", userDao.encryptPassword("user1"), null,"/resources/img/galleria5.jpg");				
			u1.addRole(r1);
			
			User u2 = userDao.create("tizioacaso@gmail.com", userDao.encryptPassword("user2"), null,"/resources/img/galleria23.jpg");
			u2.addRole(r2);
			User u3=userDao.create("esselunga@gmail.com", userDao.encryptPassword("user3"), null,"/resources/img/galleria24.jpg");
			u3.addRole(r2);
			Person p2=personDao.create("Loris", "de luigi",LocalDate.of(1992, 4, 14),"3388775899", "informatica, ingegneria",u2);
			Person p1=personDao.create("Savio", "Feng", LocalDate.of(1995, 8, 25), "3588975899", "informatica, ingegneria",u1);
			Company c1=companyDao.create("Esselunga",u3);
			
			User u4=userDao.create("dark@gmail.it",userDao.encryptPassword("pass2word1"), null, "/resources/img/galleria7.jpg");
			u4.addRole(r1);
			Person p3= personDao.create("Marco", "vitale", LocalDate.of(1997, 2, 6), 
					"3387675899", "informatica, ingegneria", u4);
			
			User u5=userDao.create("luca@gmail.it", userDao.encryptPassword("passwo42rd1"), null, 
					"/resources/img/galleria9.jpg");
			u5.addRole(r1);
			Person p4=personDao.create("Luca", "Nervi", LocalDate.of(1987, 8, 11), "3333475899", "informatica, ingegneria", u5);
			
			User u6=userDao.create("pippo@outlook.it", userDao.encryptPassword("pa41ssword1"), null, "/resources/img/galleria8.jpg");
			u6.addRole(r1);
			Person p5=personDao.create("Pippo", "Baudo", LocalDate.of(1985, 8, 9), 
					"3388775899", "informatica, ingegneria",u6);
			
			
			User u7=userDao.create("gamestop@email.it", userDao.encryptPassword("gamestop1"), 
					"GameStop Corporation, noto semplicemente come GameStop, � un'azienda statunitense con sede nella citt� di Grapevine. � il pi� grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic", 
					"/resources/img/companies/gamestop.jpg");
			u7.addRole(r1);
			Company c2=companyDao.create("Gamestop",u7);

			User u8=userDao.create("sony@gmail.it",  userDao.encryptPassword("sony22"), "La Sony Corporation, � una multinazionale conglomerata giapponese fondata nel 1946 con sede a Minato, quartiere di Tokyo. Sony si concentra principalmente sull'elettronica di consumo, sui videogiochi, intrattenimento e servizi finanziari.", 
					"/resources/img/companies/sony.jpg");
			u8.addRole(r1);
			Company c3=companyDao.create("Sony", u8);
			
			User u9=userDao.create("samsung@email.it", userDao.encryptPassword("samsun1sg"), "Samsung � un'azienda multinazionale fondata il 1� marzo 1938 da Lee Byung-chul nella citt� di Taegu, nell'attuale Corea del Sud. Comprende filiali in 58 paesi nonch� numerose aziende affiliate, la maggior parte con il nome madre Samsung, ed � il maggior conglomerato sudcoreano", 
					"/resources/img/companies/samsung.jpg");
			u9.addRole(r1);
			Company c4=companyDao.create("Samsung",u9);
/*
	
			
			Person p5= personDao.create("luca@gmail.it", "passwo42rd1", null, 
					"/resources/img/galleria9.jpg", "Luca", "Nervi", LocalDate.of(1987, 8, 11), 
					"3333475899", "informatica, ingegneria", false);
			
			Person p6= personDao.create("infojob@gmail.it", "pa3ssw4ord1", null, 
					"/resources/img/galleria10.jpg", "Sergio", "Ferrari", LocalDate.of(1998, 7, 23), 
					"3388775899", "informatica, ingegneria", false);
			
			Person p7= personDao.create("paperino@outlook.it", "pas65sword1", null, 
					"/resources/img/galleria11.jpg", "Paperino", "Pippa", LocalDate.of(1991, 12, 25), 
					"3389675899", "informatica, ingegneria", false);
			
			Person p8= personDao.create("paolo43@outlook.it", "p4assw6ord1", null, 
					"/resources/img/galleria12.jpg", "Paolo", "Bonolis", LocalDate.of(1992, 4, 2), 
					"3388475899", "informatica, ingegneria", false);
			
			Person p9= personDao.create("marco11@outlook.it", "pa4ssw5ord1", null, 
					"/resources/img/galleria13.jpg", "Marco", "Volo", LocalDate.of(1993, 9, 24), 
					"3382775899", "Informatica, Ingegneria", false);
			
			Person p10= personDao.create("deluigi@yahoo.it", "pas5sw6ord1", null, 
					"/resources/img/galleria14.jpg", "Luigi", "Di luigi", LocalDate.of(1995, 6, 15), 
					"3388675899", "Biomedica, Ingegneria", false);
			
			Person p11= personDao.create("sara@yahoo.it", "p4ss6word1", null, 
					"/resources/img/galleria15.jpg", "Sara", "Smith", LocalDate.of(1976, 11, 17), 
					"3389275899", "Meccanica, Ingegneria", false);
			
			Person p12= personDao.create("luca@yahoo.it", "2passw4ord1", null, 
					"/resources/img/galleria16.jpg", "Luca", "Kessisoglu", LocalDate.of(1988, 8, 19), 
					"3387375899", "Matematica, Ingegneria", false);
			
			Person p13= personDao.create("peppino@yahoo.it", "pas3sw4ord1", null, 
					"/resources/img/galleria17.jpg", "Peppino", "Peppina", LocalDate.of(1989, 8, 5), 
					"3384575899", "Fisica", false);
			
			Person p14= personDao.create("Marcus23@gmail.it", "pas7swo6rd1", null, 
					"/resources/img/galleria18.jpg", "Marco", "Leo", LocalDate.of(1995, 12, 18), 
					"3387575899", "Chimica", false);
			
			Person p15= personDao.create("simonedisaverio@gmail.com", "pass8word1", null, 
					"/resources/img/galleria19.jpg", "Simone", "Di saverio", LocalDate.of(1977, 5, 11), 
					"3388375899", "informatica, ingegneria", false);
			
			Person p16= personDao.create("giuseppecosta@yahoo.it", "pa7ssword1", null, 
					"/resources/img/galleria20.jpg", "Giuseppe", "Costantini", LocalDate.of(1969, 8, 17), 
					"3588575899", "informatica, ingegneria", false);
			
			Person p17= personDao.create("fede5@yahoo.it", "pass9word1", null, 
					"/resources/img/galleria21.jpg", "Federico", "Di luca", LocalDate.of(1995, 8, 13), 
					"3589475899", "Lingue,Inglese,Russo", false);
			
			Person p18= personDao.create("demeio@yahoo.it", "pa6ss4word1", null, 
					"/resources/img/galleria22.jpg", "Paolo", "De meio", LocalDate.of(1995, 5, 12), 
					"3583775899", "Matematica", false);
			
			Person p19= personDao.create("papacastoro@yahoo.it", "pass3wo4rd1", null, 
					"/resources/img/galleria23.jpg", "Papa", "Castoro", LocalDate.of(1993, 8, 26), 
					"3583575899", "informatica", false);
			
			Person p20= personDao.create("santo@yahoo.it", "p3ass5word1", null, 
					"/resources/img/galleria24.jpg", "Santo", "Diavolo", LocalDate.of(1995, 9, 23), 
					"3586475899", "informatica", false);
			
			Person p21= personDao.create("lorenzogiuliani@gmail.it", "pas1sw4ord1", null, 
					"/resources/img/galleria25.jpg", "Lorenzo", "Giuliani", LocalDate.of(1994, 8, 3), 
					"3587675899", "Informatica, Ingegneria", false);
			
			Person p22= personDao.create("white@gmail.it", "pa2ssw5ord1", null, 
					"/resources/img/galleria26.jpg", "Bianco", "Rossi", LocalDate.of(1995, 5, 6), 
					"3588835899", "Informatica, Ingegneria", false);
			
			Person p23= personDao.create("coccodemamma@gmail.it", "pa3ssw4ord1", null, 
					"/resources/img/galleria27.jpg", "Cocco", "Bello", LocalDate.of(1993, 2, 11), 
					"3458975899", "Meccanica, Ingegneria", false);
			
			Person p24= personDao.create("engineer@gmail.it", "p2assw4ord1", null, 
					"/resources/img/galleria28.jpg", "Marco", "Sante", LocalDate.of(1985, 8, 21), 
					"3658975899", "Informatica, Ingegneria", false);
			
			Person p25= personDao.create("sera98@gmail.it", "p4assw4ord1", null, 
					"/resources/img/galleria29.jpg", "Serafina", "Lucia", LocalDate.of(1995, 2, 13), 
					"3858975899", "Informatica, Ingegneria", false);
			
			Person p26= personDao.create("francy@gmail.it", "pas2sw4rd1", null, 
					"/resources/img/galleria30.jpg", "Francesca", "Montecchiari", LocalDate.of(1992, 8, 15), 
					"3138975899", "Informatica", false);
			
			Person p27= personDao.create("topolino@gmail.it", "pa8ss9word1", null, 
					"/resources/img/galleria31.jpg", "Topo", "Lino", LocalDate.of(1995, 5, 4), 
					"3648975899", "Elettronica, Ingegneria", false);
			
			Person p28= personDao.create("marianne@gmail.it", "pa4ssw0ord1", null, 
					"/resources/img/galleria32.jpg", "Marianne", "Killer", LocalDate.of(1991, 8, 14), 
					"3748975899", "Elettronica, Ingegneria", false);
			
			Person p29= personDao.create("thunder@gmail.it", "pa2ssw6ord1", null, 
					"/resources/img/galleria33.jpg", "Tuono", "Di luce", LocalDate.of(1991, 6, 7), 
					"3168975899", "Meccanica, Ingegneria", false);
			
			Person p30= personDao.create("luce@hotmail.it", "pas3swo5rd1", null, 
					"/resources/img/galleria34.jpg", "Luce", "Bianca", LocalDate.of(1997, 1, 9), 
					"3748975899", "Informatica, Ingegneria", false);
			
			Person p31= personDao.create("nana@gmail.it", "pa3ss6word1", null, 
					"/resources/img/galleria35.jpg", "Nana", "Di antonio", LocalDate.of(1985, 1, 19), 
					"3868975899", "Informatica", false);
			
			Person p32= personDao.create("maria@gmail.it", "pa2ssw5ord1", null, 
					"/resources/img/galleria36.jpg", "Maria", "Feng", LocalDate.of(1984, 4, 28), 
					"3658975899", "Biologia", false);
			
			Person p33= personDao.create("marta@gmail.it", "pa8ssw6ord1", null, 
					"/resources/img/galleria37.jpg", "Marta", "Feng", LocalDate.of(1985, 8, 21), 
					"3788975899", "Biomedica, Ingegneria", false);
			
			Person p34= personDao.create("deloitte@gmail.it", "pa5sswo7rd1", null, 
					"/resources/img/galleria38.jpg", "Marco", "Feng", LocalDate.of(1995, 12, 31), 
					"3638975899", "Informatica, Ingegneria", false);
			
			Person p35= personDao.create("tech@gmail.it", "pa3ssw6ord1", null, 
					"/resources/img/galleria39.jpg", "Luigi", "Feng", LocalDate.of(1988, 9, 11), 
					"3158975899", "Informatica, Ingegneria", false);
			
			Person p36= personDao.create("bimbo@gmail.it", "pa4ssw5ord1", null, 
					"/resources/img/galleria40.jpg", "Loris", "Feng", LocalDate.of(1981, 12, 5), 
					"3238975899", "Fisica", false);
					*/
			
//Company			
 
			/*

			
			Company c5=companyDao.create("HP", "hp@gmail.it", "hpack", 
					"La Hewlett-Packard � una multinazionale statunitense dell'informatica attiva sia nel mercato dell'hardware che in quello del software e dei servizi collegati all'informatica. Ad inizio 2011 era il primo produttore mondiale di computer portatili per unit� vendute.", 
					"/resources/img/companies/hp.jpg", false);
			
			Company c6=companyDao.create("Alitalia", "alitalia@gmail.it", "alit2", 
					"Alitalia - Societ� Aerea Italiana S.p.A. in a.s, o semplicemente Alitalia, � la maggiore compagnia aerea, nonch� compagnia aerea di bandiera, dell'Italia, attualmente in amministrazione straordinaria.", 
					"/resources/img/companies/hp.jpg", false);
*/
			
			
			
	
			
			
			
			
//joboffer
			JobOffer j1=jobOfferDao.create("Lombardia", "Mantova", "Mantova", "ADDETTO AL REPARTO GASTRONOMIA",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Passione per la lavorazione/trasformazione delle materie prime\r\n" + 
					"· Propensione all'utilizzo delle attrezzature da taglio e lavorazione\r\n" + 
					"· Predisposizione al Servizio al Cliente\r\n" + 
					"· Attitudini alle relazioni interpersonali\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Precisione e serietà\r\n" + 
					"· Preferibile esperienza pregressa nel ruolo, seppur di breve durata, maturata preferibilmente in contesti GDO.", 
					"determinato", Education.LICENZA_MEDIA, "1 anno",LocalDate.of(2019, 12, 25), c1);
			
			
			JobOffer j2=jobOfferDao.create("Lazio", "Roma", "Roma", "Commesso GameStop",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Passione per i giochi e esperienza videoludica\r\n" + 
					"· Gentilezza e cordialit�\r\n" + 
					"· Predisposizione al Servizio al Cliente\r\n" + 
					"· Attitudini alle relazioni interpersonali\r\n" + 
					"· Capacità di sopportare lo stress\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Seriet�\r\n" + 
					"· Capacit� di utilizzo del computer.", 
					"determinato", Education.DIPLOMA_DI_MATURITA, "6 mesi",LocalDate.of(2019, 12, 25), c1);
			

			
			JobOffer j3=jobOfferDao.create("Campania", "Napoli", "Napoli" , "Programmatore Java",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Conoscenza del linguaggio di programmazione Java\r\n" + 
					"· Capacit� di adattamento \r\n" + 
					"· Conoscensza della lingua inglese\r\n" + 
					"· Voglia di crescere e imparare\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Esperienza di almeno 3 anni\r\n" + 
					"· Preferibilmente lavoratore remoto", 
					"determinato", Education.LAUREA_TRIENNALE, "3 anni",LocalDate.of(2019, 12, 25), c1);
			
			
			
			JobOffer j4=jobOfferDao.create("Puglia", "Bari", "Bari", "Assisente di volo",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Esperienza di 3 anni\r\n" + 
					"· Conoscenza dell'inglese\r\n" + 
					"· Predisposizione al Servizio al Cliente\r\n" + 
					"· Gentilezza e empatia\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Disposto a voli internazionali�\r\n" + 
					"· Capacit� di comunicazione", 
					"determinato", Education.LAUREA_SPECIALISTICA, "1 anno",LocalDate.of(2019, 12, 25), c1);
			
			
			JobOffer j5=jobOfferDao.create("Marche", "Ancona", "Jesi", "Programmatore Cad",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Esperienza di almeno 5 anni con AutoCAD\r\n" + 
					"· Autonomia\r\n" + 
					"· Conoscenza dell'inglese\r\n" + 
					"· Sopportazione allo stress\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Precisione e serietà\r\n" + 
					"· Disposto a trasferirsi nella sede centrale a Roma", 
					"Indeterminato", Education.LAUREA_SPECIALISTICA, "indeterminato",LocalDate.of(2019, 12, 25),c1);
			
			
			
			Curriculum c11=curriculumDao.create(new Curriculum(p1, "01/2005–alla data attuale Assistente amministrativo\r\n" + 
					"Alma Mater Studiorum\r\n" + 
					"Via Zamboni 37, 40126 Bologna\r\n" + 
					"Gestione della documentazione contabile generale, fiscale e tributaria, relazione con la clientela", 
					"Istituto Magistrale Maria Montessori, Via Mazzini 5 - 40123 Bologna\r\n" + 
					"▪ espressione italiana\r\n" + 
					"▪ matematica\r\n" + 
					"▪ scienze\r\n" + 
					"▪ lingua straniera (inglese, francese, tedesco)", 
					"Capacità di lavorare in gruppo maturata in molteplici situazioni in cui era indispensabile la\r\n" + 
					"collaborazione tra figure diverse e con modalità orarie varie (turni, fine settimana)", 
					"scrittura creativa: corso presso l'Informagiovani del Comune di Bologna"));
			
			
			
			// phase 2 : navigate data in the database
			
	
			
			p1=personDao.apply(p1, j1);
			p2=personDao.apply(p2, j1);
			p1=personDao.apply(p1, j3);
			p3=personDao.apply(p3, j1);
			p4=personDao.apply(p4, j1);
			p5=personDao.apply(p5, j1);
			p3=personDao.apply(p2, j2);
			p4=personDao.apply(p4, j2);
			p5=personDao.apply(p5, j4);
			p5=personDao.apply(p5, j5);
			
			
			/*
			personDao.apply(p6, j1);
			personDao.apply(p7, j1);
			personDao.apply(p8, j1);
			personDao.apply(p9, j1);
			personDao.apply(p10, j1);
			personDao.apply(p11, j1);
			personDao.apply(p12, j1);
			personDao.apply(p13, j1);
			personDao.apply(p14, j1);
			personDao.apply(p15, j1);
			personDao.apply(p16, j1);
			personDao.apply(p17, j1);
			personDao.apply(p18, j1);
			personDao.apply(p19, j1);
			personDao.apply(p20, j1);
			personDao.apply(p21, j1);
			personDao.apply(p22, j1);
			personDao.apply(p23, j1);
			personDao.apply(p24, j2);
			personDao.apply(p25, j2);
			personDao.apply(p26, j2);
			personDao.apply(p27, j2);
			personDao.apply(p28, j2);
			personDao.apply(p29, j2);
			personDao.apply(p30, j2);
			personDao.apply(p31, j2);
			personDao.apply(p32, j2);
			personDao.apply(p33, j2);
			personDao.apply(p34, j2);
			personDao.apply(p35, j2);*/
			//personDao.apply(p1, j1);; lancia un'eccezione perchè non ci si può candidare due volte per la stessa offerta
				

			
			session.getTransaction().commit();

			//DELETE JOBOFFER
			/*
			
			session.beginTransaction();
			for (Person p:j1.getCandidancies()) {
				p.getCandidacies().remove(j1);
			}
			j1.getCandidancies().clear();
			jobOfferDao.update(j1);
			Company company=j1.getCompany();
			company.getJobOffers().remove(j1);
			jobOfferDao.delete(j1);
			session.getTransaction().commit();*/
			
			//DELETE COMPANY
			/*session.beginTransaction();
			for (JobOffer j:c1.getJobOffers()) {
				jobOfferDao.delete(j);
			}
			c1.getJobOffers().clear();
			c1=companyDao.update(c1);
			companyDao.delete(c1); 
			session.getTransaction().commit();
			
			*/
			
			//DELETE USER
			/*session.beginTransaction();
			
			u3.getRoles().clear();
			userDao.update(u3);
			userDao.delete(u3);
			session.getTransaction().commit();
			*/
			/*
			 * 
			//DELETE PERSON
			session.beginTransaction();
			for (JobOffer j:p1.getCandidacies()) {
				j.getCandidancies().remove(p1);
			};
			p1.getCandidacies().clear();
			personDao.delete(p1);
			session.getTransaction().commit();*/
			
			//DELETE CURRICULUM
			/*
			session.beginTransaction();
			Person p=c11.getPerson();
			p.setCurriculum(null);
			p=personDao.update(p);
			curriculumDao.delete(c11);
			session.getTransaction().commit();
			*/
			assert(personDao.findbyUserId("saviofeng@gmail.it")==p1);

			}
		

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
	

}
