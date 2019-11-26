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

			
			// Popolamento dei dati nel database 
//Person			
			Person p1= personDao.create("saviofeng@gmail.it", "c3asa2", null, 
					"/resources/img/galleria5.jpg", "Savio", "Feng", LocalDate.of(1995, 8, 25), 
					"3588975899", "informatica, ingegneria", false);
			
			Person p2= personDao.create("loris@gmail.it", "passw3ord1", null, 
					"/resources/img/galleria6.jpg", "Loris", "de luigi", LocalDate.of(1992, 4, 14), 
					"3388775899", "informatica, ingegneria", false);
			
			Person p3= personDao.create("dark@gmail.it", "pass2word1", null, 
					"/resources/img/galleria7.jpg", "Marco", "vitale", LocalDate.of(1997, 2, 6), 
					"3387675899", "informatica, ingegneria", false);
			
			Person p4= personDao.create("pippo@outlook.it", "pa41ssword1", null, 
					"/resources/img/galleria8.jpg", "Pippo", "Baudo", LocalDate.of(1985, 8, 9), 
					"3388775899", "informatica, ingegneria", false);
			
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
			
//Company			
			Company c1=companyDao.create("Esselunga", "esselunga@gmail.it", "esselung23", 
					"ESSELUNGA � una delle principali catene italiane nel settore della grande distribuzione che opera attraverso una rete di oltre 150 superstore e supermarket in Lombardia, Toscana, Emilia Romagna, Piemonte, Veneto, Liguria e Lazio. La storia di Esselunga inizia nel 1957 con l’apertura a Milano del primo supermercato in Italia; oggi il gruppo, con sede centrale a Limito di Pioltello, nell’hinterland Est di Milano, è costituito da oltre 21.000 dipendenti, fattura oltre 7 miliardi di euro e detiene una quota di mercato pari al 12 %.\r\n" + 
					"L’azienda è costantemente impegnata nell’innovazione di prodotto, nella salvaguardia dell’ambiente e nella tutela del consumatore: produttore oltre che distributore, Esselunga ha tra i suoi punti di forza i prodotti a proprio marchio e i prodotti freschi.", 
					"/resources/img/companies/esselunga.jpg", false);
			
			Company c2=companyDao.create("Gamestop", "gamestop@email.it", "gamestop1", 
					"GameStop Corporation, noto semplicemente come GameStop, � un'azienda statunitense con sede nella citt� di Grapevine. � il pi� grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic", 
					"/resources/img/companies/gamestop.jpg", false);
			
			Company c3=companyDao.create("Sony", "sony@gmail.it", "sony22", 
					"La Sony Corporation, � una multinazionale conglomerata giapponese fondata nel 1946 con sede a Minato, quartiere di Tokyo. Sony si concentra principalmente sull'elettronica di consumo, sui videogiochi, intrattenimento e servizi finanziari.", 
					"/resources/img/companies/sony.jpg", false);
			
			Company c4=companyDao.create("Samsung", "samsung@email.it", "samsun1sg", 
					"Samsung � un'azienda multinazionale fondata il 1� marzo 1938 da Lee Byung-chul nella citt� di Taegu, nell'attuale Corea del Sud. Comprende filiali in 58 paesi nonch� numerose aziende affiliate, la maggior parte con il nome madre Samsung, ed � il maggior conglomerato sudcoreano", 
					"/resources/img/companies/samsung.jpg", false);
			
			Company c5=companyDao.create("HP", "hp@gmail.it", "hpack", 
					"La Hewlett-Packard � una multinazionale statunitense dell'informatica attiva sia nel mercato dell'hardware che in quello del software e dei servizi collegati all'informatica. Ad inizio 2011 era il primo produttore mondiale di computer portatili per unit� vendute.", 
					"/resources/img/companies/hp.jpg", false);
			
			Company c6=companyDao.create("Alitalia", "alitalia@gmail.it", "alit2", 
					"Alitalia - Societ� Aerea Italiana S.p.A. in a.s, o semplicemente Alitalia, � la maggiore compagnia aerea, nonch� compagnia aerea di bandiera, dell'Italia, attualmente in amministrazione straordinaria.", 
					"/resources/img/companies/hp.jpg", false);

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
					"determinato", "licenza media", "1 anno", c1);
			
			
			JobOffer j2=jobOfferDao.create("Milano", "Roma", "Bologna", "Commesso GameStop",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Passione per i giochi e esperienza videoludica\r\n" + 
					"· Gentilezza e cordialit�\r\n" + 
					"· Predisposizione al Servizio al Cliente\r\n" + 
					"· Attitudini alle relazioni interpersonali\r\n" + 
					"· Capacità di sopportare lo stress\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Seriet�\r\n" + 
					"· Capacit� di utilizzo del computer.", 
					"determinato", "diploma", "6 mesi", c2);
			
			
			JobOffer j3=jobOfferDao.create("Lombardia", "Napoli", "Roma" , "Programmatore Java",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Conoscenza del linguaggio di programmazione Java\r\n" + 
					"· Capacit� di adattamento \r\n" + 
					"· Conoscensza della lingua inglese\r\n" + 
					"· Voglia di crescere e imparare\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Esperienza di almeno 3 anni\r\n" + 
					"· Preferibilmente lavoratore remoto", 
					"determinato", "Laurea in Informatica/Ing Inf.", "3 anni", c4);
			
			
			
			JobOffer j4=jobOfferDao.create("Puglia", "Abruzzo", "Mantova", "Assisente di volo",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Esperienza di 3 anni\r\n" + 
					"· Conoscenza dell'inglese\r\n" + 
					"· Predisposizione al Servizio al Cliente\r\n" + 
					"· Gentilezza e empatia\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Disposto a voli internazionali�\r\n" + 
					"· Capacit� di comunicazione", 
					"determinato", "Diploma", "1 anno", c6);
			
			
			
			JobOffer j5=jobOfferDao.create("Roma", "Firenze", "Ancona", "Programmatore Cad",
					"Si richiedono le seguenti caratteristiche:\r\n" + 
					"· Esperienza di almeno 5 anni con AutoCAD\r\n" + 
					"· Autonomia\r\n" + 
					"· Conoscenza dell'inglese\r\n" + 
					"· Sopportazione allo stress\r\n" + 
					"· Capacità di lavorare in team\r\n" + 
					"· Ambizione e predisposizione al miglioramento continuo\r\n" + 
					"· Precisione e serietà\r\n" + 
					"· Disposto a trasferirsi nella sede centrale a Roma", 
					"Indeterminato", "Laurea in Ingegneria Meccancia", "indeterminato", c3);
			
			
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
