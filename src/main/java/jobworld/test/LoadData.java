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

			
			// Aggiunta dei dati al database 
			
			Person p1= personDao.create("savioFengXiao@email.ii", "password1", null, 
					"/resources/img/galleria5.jpg", "Xiao", "Feng", LocalDate.of(1995, 8, 11), 
					"3588975899", "informatica, ingegneria", false);
			Company c1=companyDao.create("Esselunga", "esselunga@email.it", "essepassword", 
					"ESSELUNGA è una delle principali catene italiane nel settore della grande distribuzione che opera attraverso una rete di oltre 150 superstore e supermarket in Lombardia, Toscana, Emilia Romagna, Piemonte, Veneto, Liguria e Lazio. La storia di Esselunga inizia nel 1957 con l’apertura a Milano del primo supermercato in Italia; oggi il gruppo, con sede centrale a Limito di Pioltello, nell’hinterland Est di Milano, è costituito da oltre 21.000 dipendenti, fattura oltre 7 miliardi di euro e detiene una quota di mercato pari al 12 %.\r\n" + 
					"L’azienda è costantemente impegnata nell’innovazione di prodotto, nella salvaguardia dell’ambiente e nella tutela del consumatore: produttore oltre che distributore, Esselunga ha tra i suoi punti di forza i prodotti a proprio marchio e i prodotti freschi.", 
					"/resources/img/companies/esselunga.jpg", false);
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
					"determinato", "licenza media", "1 anno", c1);
			
			Curriculum c11=curriculumDao.create(p1, "01/2005–alla data attuale Assistente amministrativo\r\n" + 
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
					"scrittura creativa: corso presso l'Informagiovani del Comune di Bologna");
			
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
