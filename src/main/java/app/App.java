package app;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.dao.CompanyDao;
import model.dao.JobOfferDao;
import model.dao.PersonDao;
import model.entities.Company;
import model.entities.JobOffer;
import model.entities.Person;

public class App {

	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Entrato ...");

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigurator.class)) {

			CompanyDao companyDao = ctx.getBean(CompanyDao.class);
			JobOfferDao jobOfferDao = ctx.getBean(JobOfferDao.class);
			PersonDao personDao = ctx.getBean(PersonDao.class);

			// phase 1 : add data to database

			Company c1 = companyDao.create("sidagroup@email.it", "prova1",
					"Sida Group nasce ad Ancona nel 1985 con l'obiettivo di offrire ad aziende ed Enti pubblici e privati, servizi integrati di consulenza, sia a livello di direzione aziendale, attraverso l'elaborazione di piani strategici, sia a livello operativo, attraverso supporti per 'implementazione degli stessi, sia a livello organizzativo attraverso la ricerca, selezione e formazione del personale. La mission del Gruppo consiste nell'assistere le aziende nel proprio sviluppo e consolidamento, stimolando processi di cambiamento e contribuendo ad accrescere la cultura d'impresa. Attualmente Sida Group,è tra le prime 10 società di consulenza italiane per dimensione e tipologia di intervento e, proprio in virtù della visione integrata dei processi aziendali e del contributo di oltre 140 professionisti, annovera tra i propri clienti circa 1800 aziende, 40 istituti bancari, 80 enti pubblici e 60 studi professionali. Tra le eccellenze del proprio patrimonio esperienziale figurano prestigiose pubblicazioni su tematiche innovative di gestione d¿impresa e progetti di sviluppo imprenditoriale sui mercati internazionali. CONSULENZA STRATEGICA DIREZIONALE Immaginare il mondo di domani prima degli altri per acquisire nuovi vantaggi competitivi. Sida Group da anni supporta la Direzione Aziendale in tutte le fasi strategiche (di analisi degli scenari e proiezioni precompetitive; decisionali e progettuali di lungo periodo; di riorganizzazione e ristrutturazione; di passaggio generazionale) per raggiungere l¿eccellenza e vincere le sfide di domani. CONSULENZA OPERATIVA I consulenti Sida, organizzati in divisioni operative, affiancano le aziende nella gestione delle loro attività e principali aree funzionali: Strategia e sviluppo organizzativo, Finanza, Amministrazione e Controllo di Gestione, Fiscale, societaria e legale, Operations (logistica, produzione e qualità), Marketing e sviluppo commerciale, Risorse umane (coaching, ricerca e selezione del personale), Cost Saving, Internazionalizzazione, Merger & Acquisition e Family Business Center. FORMAZIONE Crescita, sviluppo ed aggiornamento professionale delle risorse umane sono i fattori di successo per le imprese pubbliche e private. Sida, grazie alla propria Scuola di Formazione, accreditata in diverse regioni italiane, sviluppa progetti formativi rivolti a imprenditori, managers, quadri, giovani laureandi e neo-laureati, quali: Master, Corsi in House, Corsi Interaziendali, Focus Group e Best Practices e Formazione Finanziata. Oltre 3000 gli operatori formati, oltre 500 i percorsi formativi realizzati. www.mastersida.com SIDA NEL MONDO Filiali: USA, ROMANIA, INGHILTERRA, IRLANDA, POLONIA, EMIRATI ARABI, CINA, SVIZZERA. Corrispondenti: BRASILE, INDIA, REPUBBLICA CECA, RUSSIA, TUNISIA, UCRANIA.");

			JobOffer j1 = jobOfferDao.create("Marche", "Ancona", "Ancona",
					"Assistente alla Direzione settore hotellerie",
					"Lindbergh hotels è una catena alberghiera che conta 8 strutture, di 4 e 5 stelle, dislocate sul territorio italiano, con un organico di 400 dipendenti. La società in questione si occupa della gestione e dello sviluppo delle stesse e del personale in forza, con particolare attenzione verso la propria clientela. In collaborazione con Sida Group ricerca per un "
							+ "tirocinio formativo previa formazione un Assistente alla Direzione."
							+ "Il tirocinante, inserito in un contesto di front/back office, dovrà dimostrare di avere ottime capacità comunicative e di problem - solving, di sapere come si organizza il personale in strutture Leisure e Business, nonché fornire supporto al cliente. Attraverso questo percorso formativo, il tirocinante dovrà rispondere direttamente alla Direzione e operare secondo gli obiettivi aziendali sempre nel "
							+ "rispetto delle scadenze prestabilite, con massima riservatezza.",
					"Stage", "Laurea breve (3 anni)", "Non richiesta", c1);

			Person p1 = personDao.create("giangis@hotmail.it", "passw",
					"• Diploma di Laurea in economia e gestione aziendale conseguito il 10 febbraio 2004 presso l’Università di Bologna\r\n"
							+ "• Argomento tesi: “...........”. Relatore: prof. ……….., docente di ..................\r\n"
							+ "• Voto di laurea: .…..\r\n"
							+ "• Diploma di maturità scientifica conseguito il 26 giugno 2000 presso il Liceo scientifico Leonardo da Vinci di Pescara con il punteggio di 90/100",
					"Marco", "Abbate", LocalDate.of(1990, 11, 25), "320000255", "curriculumURL",
					"Informatica, Ristorazione");
			JobOffer j2 = jobOfferDao.create("Marche", "Ancona", "Ancona",
					"Assistente alla Direzione settore hotellerie",
					"Lindbergh hotels è una catena alberghiera che conta 8 strutture, di 4 e 5 stelle, dislocate sul territorio italiano, con un organico di 400 dipendenti. La società in questione si occupa della gestione e dello sviluppo delle stesse e del personale in forza, con particolare attenzione verso la propria clientela. In collaborazione con Sida Group ricerca per un "
							+ "tirocinio formativo previa formazione un Assistente alla Direzione."
							+ "Il tirocinante, inserito in un contesto di front/back office, dovrà dimostrare di avere ottime capacità comunicative e di problem - solving, di sapere come si organizza il personale in strutture Leisure e Business, nonché fornire supporto al cliente. Attraverso questo percorso formativo, il tirocinante dovrà rispondere direttamente alla Direzione e operare secondo gli obiettivi aziendali sempre nel "
							+ "rispetto delle scadenze prestabilite, con massima riservatezza.",
					"Stage", "Laurea breve (3 anni)", "Non richiesta", c1);
			p1.apply(j1);
			p1.apply(j2);
			jobOfferDao.update(j1);
			jobOfferDao.update(j2);

			List<JobOffer> jobOffers = jobOfferDao.findAll();
			for (JobOffer j : jobOffers) {
				System.out.println(j);
			}

			List<JobOffer> jobOffers2 = jobOfferDao.findbyRegion("Abruzzo");
			for (JobOffer j : jobOffers2) {
				System.out.println(j);
			}

		} catch (Exception e) {
			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		logger.info("Esco ...");
	}

}
