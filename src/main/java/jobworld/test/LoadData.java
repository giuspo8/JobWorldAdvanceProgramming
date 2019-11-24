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
			 
			
			Company c1 = companyDao.create("test@as.it", "prova1",
					"Sida Group nasce ad Ancona nel 1985 con l'obiettivo di offrire ad aziende ed Enti pubblici e privati, servizi integrati di consulenza, sia a livello di direzione aziendale, attraverso l'elaborazione di piani strategici, sia a livello operativo, attraverso supporti per 'implementazione degli stessi, sia a livello organizzativo attraverso la ricerca, selezione e formazione del personale. La mission del Gruppo consiste nell'assistere le aziende nel proprio sviluppo e consolidamento, stimolando processi di cambiamento e contribuendo ad accrescere la cultura d'impresa. Attualmente Sida Group,� tra le prime 10 societ� di consulenza italiane per dimensione e tipologia di intervento e, proprio in virt� della visione integrata dei processi aziendali e del contributo di oltre 140 professionisti, annovera tra i propri clienti circa 1800 aziende, 40 istituti bancari, 80 enti pubblici e 60 studi professionali. Tra le eccellenze del proprio patrimonio esperienziale figurano prestigiose pubblicazioni su tematiche innovative di gestione d�impresa e progetti di sviluppo imprenditoriale sui mercati internazionali. CONSULENZA STRATEGICA DIREZIONALE Immaginare il mondo di domani prima degli altri per acquisire nuovi vantaggi competitivi. Sida Group da anni supporta la Direzione Aziendale in tutte le fasi strategiche (di analisi degli scenari e proiezioni precompetitive; decisionali e progettuali di lungo periodo; di riorganizzazione e ristrutturazione; di passaggio generazionale) per raggiungere l�eccellenza e vincere le sfide di domani. CONSULENZA OPERATIVA I consulenti Sida, organizzati in divisioni operative, affiancano le aziende nella gestione delle loro attivit� e principali aree funzionali: Strategia e sviluppo organizzativo, Finanza, Amministrazione e Controllo di Gestione, Fiscale, societaria e legale, Operations (logistica, produzione e qualit�), Marketing e sviluppo commerciale, Risorse umane (coaching, ricerca e selezione del personale), Cost Saving, Internazionalizzazione, Merger & Acquisition e Family Business Center. FORMAZIONE Crescita, sviluppo ed aggiornamento professionale delle risorse umane sono i fattori di successo per le imprese pubbliche e private. Sida, grazie alla propria Scuola di Formazione, accreditata in diverse regioni italiane, sviluppa progetti formativi rivolti a imprenditori, managers, quadri, giovani laureandi e neo-laureati, quali: Master, Corsi in House, Corsi Interaziendali, Focus Group e Best Practices e Formazione Finanziata. Oltre 3000 gli operatori formati, oltre 500 i percorsi formativi realizzati. www.mastersida.com SIDA NEL MONDO Filiali: USA, ROMANIA, INGHILTERRA, IRLANDA, POLONIA, EMIRATI ARABI, CINA, SVIZZERA. Corrispondenti: BRASILE, INDIA, REPUBBLICA CECA, RUSSIA, TUNISIA, UCRANIA.");

			JobOffer j1 = jobOfferDao.create("Marche", "Ancona", "Ancona",
					"Assistente alla Direzione settore hotellerie",
					"Lindbergh hotels � una catena alberghiera che conta 8 strutture, di 4 e 5 stelle, dislocate sul territorio italiano, con un organico di 400 dipendenti. La societ� in questione si occupa della gestione e dello sviluppo delle stesse e del personale in forza, con particolare attenzione verso la propria clientela. In collaborazione con Sida Group ricerca per un "
							+ "tirocinio formativo previa formazione un Assistente alla Direzione."
							+ "Il tirocinante, inserito in un contesto di front/back office, dovr� dimostrare di avere ottime capacit� comunicative e di problem - solving, di sapere come si organizza il personale in strutture Leisure e Business, nonch� fornire supporto al cliente. Attraverso questo percorso formativo, il tirocinante dovr� rispondere direttamente alla Direzione e operare secondo gli obiettivi aziendali sempre nel "
							+ "rispetto delle scadenze prestabilite, con massima riservatezza.",
					"Stage", "Laurea breve (3 anni)", "Non richiesta", c1);

			Person p1 = personDao.create("test@hotmail.it", "passw",
					"Laurea in economia e gestione aziendale conseguito il 10 febbraio 2004 presso l�Universit� di Bologna",
					"Marco", "Abbate", LocalDate.of(1990, 11, 25), "320000255", "Informatica, Ristorazione");
			Person p2 = personDao.create("giangis@hotmail.it", "passw",
					"Diploma di Laurea in economia e gestione aziendale conseguito il 10 febbraio 2004 presso l�Universit� di Bologna",
					"Marco", "Abbate", LocalDate.of(1990, 11, 25), "320000255", "Informatica, Ristorazione");
			Curriculum c12 = curriculumDao.create(p2, "2PROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVA",
					"educationeducation5educationeducation5educationeducationeducationeducation",
					"personalSkillspersonalSkillspersonalSkillspersonalSkillspersonalSkillspersonalSkills",
					"additionalInfoadditionalInfoadditionalInfoadditionalInfoadditionalInfoadditionalInfo");
			Curriculum c11 = curriculumDao.create(p1, "PROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVA",
					"educationeducation5educationeducation5educationeducationeducationeducation",
					"personalSkillspersonalSkillspersonalSkillspersonalSkillspersonalSkillspersonalSkills",
					"additionalInfoadditionalInfoadditionalInfoadditionalInfoadditionalInfoadditionalInfo");
			JobOffer j2 = jobOfferDao.create("Marche", "Ancona", "Ancona",
					"Assistente alla Direzione settore hotellerie",
					"Lindbergh hotels � una catena alberghiera asodiasdòa asldka sldkj alsdkja lskdj asldj asldkjasdkj",
					"Stage", "Laurea breve (3 anni)", "Non richiesta", c1);
			p1.apply(j1);
			j2.setPublicationDate(LocalDate.of(2018, 10, 22));
			p1.apply(j2);
			p2.apply(j1);
			jobOfferDao.update(j1);
			jobOfferDao.update(j2);
		
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
			
			
			List<JobOffer> joboffers6 = jobOfferDao.filter("Marche", "Ancona", "Ancona", "Ass", null, "Laurea",
					"non rich");
			for (JobOffer j : joboffers6) {
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
