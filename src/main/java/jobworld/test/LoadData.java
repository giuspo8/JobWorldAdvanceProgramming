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
			//parte cifratura, va messo in questo ordine per far si che si popoli senza errori
			Role r1 = roleDao.create(TypeRole.USER);
			Role r2 = roleDao.create(TypeRole.ADMIN);
			Role r3 = roleDao.create(TypeRole.COMPANY);
			
			
			//creazione dgli user con criptazione delle pasword e l'aggiunta del loro ruolo
			User u1 = userDao.create("saviofeng@gmail.it", userDao.encryptPassword("user1"), null,"galleria2.jpg");				
			u1.addRole(r1);
			
			User u15 = userDao.create("admin@mail.it", userDao.encryptPassword("admin"), null,null);				
			u15.addRole(r2);
			
			User u2 = userDao.create("tizioacaso@gmail.com", userDao.encryptPassword("user2"), null,"galleria3.jpg");
			u2.addRole(r1);
			
			User u3=userDao.create("esselunga@gmail.com", userDao.encryptPassword("user3"), null,"galleria4.jpg");
			u3.addRole(r3);
			
			User u4=userDao.create("dark@gmail.it",userDao.encryptPassword("pass2word1"), null, "/resources/img/galleria4.jpg");
			u4.addRole(r1);
			
			User u5=userDao.create("luca@gmail.it", userDao.encryptPassword("passwo42rd1"), null, "/resources/img/galleria9.jpg");
			u5.addRole(r1);
			
			User u6=userDao.create("pippo@outlook.it", userDao.encryptPassword("pa41ssword1"), null, "/resources/img/galleria8.jpg");
			u6.addRole(r1);
						
			User u7=userDao.create("gamestop@email.it", userDao.encryptPassword("gamestop1"), "GameStop Corporation, noto semplicemente come GameStop, ï¿½ un'azienda statunitense con sede nella cittï¿½ di Grapevine. ï¿½ il piï¿½ grande rivenditore di videogiochi nuovi e usati nel mondo, ma si occupa anche della vendita di accessori per videogiochi, console ed altri apparecchi elettronic", 
			"/resources/img/companies/gamestop.jpg");
			u7.addRole(r3);
			
			User u8=userDao.create("sony@gmail.it",  userDao.encryptPassword("sony22"), "La Sony Corporation, ï¿½ una multinazionale conglomerata giapponese fondata nel 1946 con sede a Minato, quartiere di Tokyo. Sony si concentra principalmente sull'elettronica di consumo, sui videogiochi, intrattenimento e servizi finanziari.", "/resources/img/companies/sony.jpg");
			u8.addRole(r3);
			
			User u9=userDao.create("samsung@email.it", userDao.encryptPassword("samsun1sg"), "Samsung ï¿½ un'azienda multinazionale fondata il 1ï¿½ marzo 1938 da Lee Byung-chul nella cittï¿½ di Taegu, nell'attuale Corea del Sud. Comprende filiali in 58 paesi nonchï¿½ numerose aziende affiliate, la maggior parte con il nome madre Samsung, ed ï¿½ il maggior conglomerato sudcoreano", "/resources/img/companies/samsung.jpg");
			u9.addRole(r3);
			
			User u10=userDao.create("GiGroup@email.it", userDao.encryptPassword("sdadsa"), "GiGroup è un agenzia per il lavoro che offre varie possibilità lavorative", "/resources/img/companies/gigroup.jpg");
			u10.addRole(r3);
			
			User u11=userDao.create("BeElite@email.it", userDao.encryptPassword("sda312dasa"), "BeElite è una società di marketing che offre diverse possibilità di crescita", "/resources/img/companies/beelite.jpg");
			u11.addRole(r3);
			
			User u12=userDao.create("Workhere@email.it", userDao.encryptPassword("ukyera"), "Workhere è un azienda di searching work", "/resources/img/companies/work.jpg");
			u12.addRole(r3);
			
		//creazione delle persone	
			Person p2=createPerson("Loris", "de luigi",LocalDate.of(1992, 4, 14),"3388775899", "informatica, ingegneria",u2,personDao);
			
			Person p1=createPerson("Savio", "Feng", LocalDate.of(1995, 8, 25), "3588975899", "informatica, ingegneria",u1,personDao);
									
			Person p3= createPerson("Marco", "vitale", LocalDate.of(1997, 2, 6), "3387675899", "informatica, ingegneria", u4, personDao);
						
			Person p4=createPerson("Luca", "Nervi", LocalDate.of(1987, 8, 11), "3333475899", "sala, alberghiero", u5, personDao);					
			
			Person p5=createPerson("Pippo", "Baudo", LocalDate.of(1985, 8, 9), "3388775899", "meccanica, professionale",u6, personDao);
			
		//creazione delle company	
			Company c1=createCompany("Esselunga",u3,companyDao);
			
			Company c2=createCompany("Gamestop",u7,companyDao);
		
			Company c3=createCompany("Sony", u8,companyDao);
			
			Company c4=createCompany("Samsung",u9, companyDao);

			Company c5=createCompany ("Gigroup",u10, companyDao);
			
			Company c6=createCompany ("BeElite",u11, companyDao);
			
			Company c7=createCompany ("Workhere",u12, companyDao);
			
			
			
			

//creazione dei curriculum da associare alla persona per fare i vari test		
			Curriculum c11=createCurriculum(new Curriculum(p1, "Assistente amministrativo\r\n" + 
			
							"Alma Mater Studiorum\r\n", 
							
							"Istituto Magistrale Maria Montessori, Via Mazzini 5 - 40123 Bologna" + 
							
							"lingua straniera (inglese, francese, tedesco)", 
							
							"Capacità  di lavorare in gruppo maturata in molteplici situazioni in cui era indispensabile la", 
							
							"scrittura creativa: corso presso l'Informagiovani del Comune di Bologna"),curriculumDao,personDao);
					
			Curriculum c12=createCurriculum(new Curriculum(p2, "Programmatore java" + 
					
							"Alma Mater Studiorum\r\n", 
			
							"Scuola scientifica maria curie, Via Roma 5 - 40123 Bologna" + 
			
							"lingua straniera (inglese)", 
			
							"Capacità  di lavorare in gruppo maturata in molteplici situazioni in cui era indispensabile la", 
			
							"Sono una persona molto motivata"),curriculumDao,personDao);
					
					
					
			Curriculum c13=createCurriculum(new Curriculum(p3, "Programmatore Java" + 
					
							"Alma Mater Studiorum\r\n", 
			
							"Istituto Magistrale Maria Montessori, Via Mazzini 5 - 40123 Bologna" + 
			
							"lingua straniera (inglese, francese)", 
			
							"Capacità  di apprendere nuovi linguaggi in poco tempo, e di lavorare in team", 
			
							"Amante dell'informatica"),curriculumDao,personDao);
					
					
			Curriculum c14=createCurriculum(new Curriculum(p4, "Cameriere" + 
					
							"Alma Mater Studiorum\r\n", 
			
							"Istituto Professionale via michele rosa 43 - 40123 Bologna" + 
			
							"lingua straniera (inglese, francese, tedesco,spagnolo)", 
			
							"Carisma e educazione", 
			
							"sto seguendo un corso di formazione per diventare cuoco"),curriculumDao,personDao);
					
			Curriculum c15=createCurriculum(new Curriculum(p5, "Programmatore Autocad" + 
					
							"Alma Mater Studiorum\r\n", 
			
							"Istituto Magistrale Maria Montessori, Via Mazzini 5 - 40123 Bologna" + 
			
							"lingua straniera (inglese)", 
			
							"Capacità di lavoro intensivo", 
			
							"Studio autonomo di altri linguaggi oltre autocad"),curriculumDao,personDao);
			
//joboffer Le prime offerte di lavoro sono state create da noi per essere testate, quelle successive sono prese da siti come Infojobs e simili.
			
			JobOffer j1=createJobOffer("Lombardia", "Mantova", "Mantova", "ADDETTO AL REPARTO GASTRONOMIA",
					
					"Si richiedono le seguenti caratteristiche:" + 
					
					"Passione per la lavorazione/trasformazione delle materie prime" + 
					
					"Propensione all'utilizzo delle attrezzature da taglio e lavorazione" + 
					
					"Predisposizione al Servizio al Cliente" + 
					
					"Attitudini alle relazioni interpersonali" + 
					
					"Capacità  di lavorare in team" + 
					
					"Ambizione e predisposizione al miglioramento continuo" + 
					
					"Precisione e serietà " + 
					
					"Preferibile esperienza pregressa nel ruolo, seppur di breve durata, maturata preferibilmente in contesti GDO.", 
					
					"determinato", Education.LICENZA_MEDIA, "1 anno",LocalDate.of(2020, 1, 13), c1,jobOfferDao,companyDao);
			
			
			JobOffer j2=createJobOffer("Lazio", "Roma", "Roma", "Commesso GameStop",
					
					"Si richiedono le seguenti caratteristiche:" + 
					
					"Passione per i giochi e esperienza videoludica" + 
							
					"Gentilezza e cordialità" + 
					
					"Predisposizione al Servizio al Cliente" + 
					
					"Attitudini alle relazioni interpersonali" + 
					
					"Capacità  di sopportare lo stress" + 
					
					"Ambizione e predisposizione al miglioramento continuo" + 
					
					"Serietà" + 
					
					"Capacità di utilizzo del computer.", 
					
					"determinato", Education.DIPLOMA_DI_MATURITA, "6 mesi",LocalDate.of(2020, 2, 29), c2,jobOfferDao,companyDao);
			

			
			JobOffer j3=createJobOffer("Campania", "Napoli", "Napoli" , "Programmatore Java",
					
					"Si richiedono le seguenti caratteristiche:" + 
					
					"Conoscenza del linguaggio di programmazione Java" + 
					
					"Capacità di adattamento " + 
					
					"Conoscensza della lingua inglese" + 
					
					"Voglia di crescere e imparare" + 
					
					"Capacità  di lavorare in team" + 
					
					"Ambizione e predisposizione al miglioramento continuo" + 
					
					"Esperienza di almeno 3 anni" + 
					
					"Preferibilmente lavoratore remoto", 
					
					"determinato", Education.LAUREA_TRIENNALE, "3 anni",LocalDate.of(2020, 12, 1), c1,jobOfferDao,companyDao);
			
			
			
			JobOffer j4=createJobOffer("Puglia", "Bari", "Bari", "Assisente di volo",
					
					"Si richiedono le seguenti caratteristiche:" + 
					
					"Esperienza di 3 anni" + 
					
					"Conoscenza dell'inglese" + 
					
					"Predisposizione al Servizio al Cliente" + 
					
					"Gentilezza e empatia" + 
					
					"Capacità  di lavorare in team" + 
					
					"Ambizione e predisposizione al miglioramento continuo" + 
					
					"Disposto a voli internazionali" + 
					
					"Capacità di comunicazione", 
					
					"determinato", Education.LAUREA_SPECIALISTICA, "1 anno",LocalDate.of(2020, 6, 15), c3,jobOfferDao,companyDao);
			
			
			JobOffer j5=createJobOffer("Marche", "Ancona", "Jesi", "Programmatore Cad",
					
					"Si richiedono le seguenti caratteristiche:" + 
					
					"Esperienza di almeno 5 anni con AutoCAD" + 
					
					"Autonomia" + 
					
					"Conoscenza dell'inglese" + 
					
					"Sopportazione allo stress" + 
					
					"Capacità  di lavorare in team" + 
					
					"Ambizione e predisposizione al miglioramento continuo" + 
					
					"Precisione e serietà " + 
					
					"Disposto a trasferirsi nella sede centrale a Roma", 
					
					"Indeterminato", Education.LAUREA_SPECIALISTICA, "indeterminato",LocalDate.of(2020, 12, 25),c4,jobOfferDao,companyDao);
			
			
			JobOffer j6=createJobOffer("Lombardia", "Milano", "Milano", "FIERA MILANO: PRIMA ESPERIENZA NEL MARKETING",
					
					"La piu grande società di comunicazione e Marketing del Mondo con origini australiane"+
			
					"Seleziona per la sede di Milano, Giovani alla prima esperienza."+
					
					"Si richiede:"+
					
					"- Forte Propensione al Contatto con il Pubblico"+
					
					"- Capacità di Lavorare in Team"+
					
					"- Carattere Solare e Dinamico"+
					
					"L' Azienda offre"+
					
					"- Formazione gratuita in marketing"+
					
					"- Ambiente giovane e dinamico"+
					
					"- Possibilità di Viaggi Nazionali e Internazionali"+
					
					"Luogo di lavoro nei principali Eventi e fiere di Milano e Provincia",
					
					"Determinato",Education.SENZA_STUDI,"1 mese", LocalDate.of(2020,2,14),c4,jobOfferDao,companyDao);
			
					
			JobOffer j7=createJobOffer("Lombardia", "Milano", "Milano", "VIAGGI, FORMAZIONE E MARKETING: PRIMA ESPERIENZA MILANO",
					
					"La piu grande società di comunicazione e Marketing del Mondo con origini australiane"+
			
					"Seleziona per la sede di Milano, Giovani alla prima esperienza."+

					"Si richiede:"+

					"- Forte Propensione al Contatto con il Pubblico"+

					"- Capacità di Lavorare in Team"+

					"- Carattere Solare e Dinamico"+

					"L' Azienda offre"+

					"- Formazione gratuita in marketing"+

					"- Ambiente giovane e dinamico"+

					"- Possibilità di Viaggi Nazionali e Internazionali"+

					"Luogo di lavoro nei principali Eventi e fiere di Milano e Provincia",
					
					"Determinato",Education.SENZA_STUDI,"1 mese", LocalDate.of(2020,1,5),c7,jobOfferDao,companyDao);
					
				
			JobOffer j8=createJobOffer("Piemonte", "Torino", "Brandizzo", "ASSISTENTE AMMINISTRAZIONE E GESTIONE IMMOBILI",
					
					"Nata nel 1999 e oggi fra i primi 10 player del mercato, ETJCA Agenzia per il lavoro e' una societa' consolidata, affidabile e competente che, attraverso la sua rete di filiali presenti in tutta Italia, offre alle persone in cerca di lavoro la possibilita' di trovare un'occupazione in linea con il proprio profilo professionale, favorendo il contatto con diverse realta' imprenditoriali."+

					"Per importante realtà con sede a Brandizzo (TO), Etjca Spa Filiale di Asti ricerca"+

					"UN ASSISTENTE ADDETTO ALL'AMMINISTRAZIONE E ALLA GESTIONE DEGLI IMMOBILI"+

					"La risorsa sarà alle dirette dipendenze della Direzione."+

					"I candidati ideali hanno le seguenti caratteristiche:"+
							 
					"- laurea (preferibilmente) in ambito economico;"+
					 
					"- buona conoscenza della lingua inglese e della lingua francese (le proprietà sono in Italia e all'estero);"+
					
					"- disponibilità ad eventuali trasferte;"+
					
					"- esperienza nella gestione amministrativa degli immobili (contratti, documentazione, adempimenti, ...);"+
					
					"- esperienza nella gestione operativa degli immobili (gestione delle maestranze per la risoluzione problematiche pratiche, sopralluoghi, gestione pagamento bollette o allacciamenti, ...);"+
					
					"- ottima conoscenza dei sistemi informativi (Word, Excel, Access, PowerPoint, Outlook);"+
					
					"- ampia disponibilità oraria;"+
					
					"- dinamismo, professionalità e serietà;"+
					
					"- eccellenti doti di comunicazione, sia orale e scritta, forte determinazione al raggiungimento degli obiettivi;"+
					
					"- resistenza allo stress e rispetto delle scadenze."+

					"Offriamo un iniziale contratto a tempo determinato in somministrazione con livello e RAL da definire in base all'effettiva esperienza."+

					"Si invitano i candidati di entrambi i sessi (L.903/77) a leggere l'informativa sulla privacy (art.13, Reg EU 679/2016)."+

					"Etjca S.p.A. (Aut. Min. Prot. N. 1309-SG del 23/02/2005),",
					
					"Determinato",Education.LAUREA_TRIENNALE,"3 anni", LocalDate.of(2020,1,28),c7,jobOfferDao,companyDao);
					
			
			
			
			JobOffer j9=createJobOffer("Lombardia", "Lecco", "Lomagna", "OPERATORE ELETTRONICO",
					
					"Ricerchiamo per prestigiosa azienda operante nel settore elettronico"+

					"OPERATORE/OPERATRICE ELETTRONICO/A"+

					"La risorsa si occupera' di assemblaggio componentistica elettronica, collaudo, riparazione schede, kitting."+

					"E' richiesto Diploma di Perito Elettronico, gradita precedente esperienza in societa' analoghe;"+
		
					"si richiede disponibilita' al lavoro su giornata e 2/3 turni."+

					"Durata contratto: una settimana piu' proroghe e possibilita' di inserimento presso azienda cliente."+

					"Zona: Lomagna (LC)"+

					"Gi Group SpA e' autorizzata ad operare dal Ministero del Lavoro (Aut. Min. 26/11/2004 PROT. 1101 - SG)."+
		
					"I candidati ambosessi (D.lgs n. 198/2006) sono invitati a leggere l'informativa privacy ai sensi degli artt. 13 e 14 del"+
		
					"Reg. EU 679/2016 al seguente indirizzo www.gigroup.it/privacy-candidati",
					
					"A giornata",Education.DIPLOMA_DI_MATURITA,"1 anno", LocalDate.of(2020,5,9),c7,jobOfferDao,companyDao);
			
		
			JobOffer j10=createJobOffer("Lombardia", "Monza", "Vimercate", "ADDETTO/A AMMINISTRAZIONE SERVICE",
					
					"Ricerchiamo per nota multinazionale operante nel settore metalmeccanico"+

					"ADDETTA BACK OFFICE AMMINISTRATIVO PER STAGE"+

					"La risorsa si occupera' di bollettazione, fatturazione, inserimento bollettini interventi tecnici, verifica condizioni di fornitura, contatto fornitori e clienti Italia."+
		
					"Si richiedono titolo di studio in ambito amministrativo o affini, buona conoscenza della lingua inglese, buona capacita' di problem solving, predisposizione all'apprendimento; gradita precedente esperienza di stage in attivita' analoga, disponibilita' a tirocinio full-time iniziale di sei mesi."+

					"Si offre stage full-time iniziale di sei mesi prorogabile di ulteriori sei mesi con successiva possibilita' di assunzione in apprendistato"+
		
					"Zona: Vimercate (MB)",
					
					"determinato",Education.DIPLOMA_DI_MATURITA,"1 anno", LocalDate.of(2020,1,15),c7,jobOfferDao,companyDao);					
		




			JobOffer j11=createJobOffer("Lombardia", "Milano", "Milano", "LAVORO IN EVENTI - gennaio 2020",
		
					"Be Elite srl , importante società di marketing e comunicazione, seleziona per ampliamento organico aziendale , 5 nuove risorse."+

					"Le figure selezionate svolgeranno attività di promoting gestiranno le campagne pubblicitarie per clienti di fama mondiale all'interno di eventi organizzati."+

					"I profili in linea con la ricerca dovranno possedere i seguenti requisiti :"+
		
					"-domicilio o residenza a milano città o provincia"+
				
					"-disponibilità immediata"+
		
					"-attitudine al lavoro di squadra"+
		
					"-buone doti comunicative e relazionali"+
		

					"L'azienda offre ai candidati selezionati:"+
		
					"-contratto a norma di legge"+
		
					"-Pagamento mensile"+
		
					"-Formazione gratuita in azienda"+
						
					"-Concrete possibilità di crescita"+
		
					"-Ambiente di lavoro dinamico, stimolante e giovanile"+

					"Per accedere alle selezioni , inviare la propria candidatura , seguirà un primo contatto telefonico.",
					
					"a giornata",Education.DIPLOMA_DI_MATURITA,"3 mesi", LocalDate.of(2020,3,11),c6,jobOfferDao,companyDao);	





			JobOffer j12=createJobOffer("Campania", "Salerno", "Baronissi", "Disegnatore meccanico",
		
					"Gi Group SpA, Agenzia per il Lavoro (Aut. Min. 26/11/04 Prot. n. 1101-SG) ricerca per importante azienda cliente del settore metalmeccanico:"+

					"Disegnatore Meccanico"+

					"Requisiti richiesti:Pregressa esperienza nella mansioneDiploma tecnicoOttima conoscenza degli strumenti meccaniciFlessibilita' e capacita' di problem solvingSede di lavoro: Baronissi (SA)"+

					"Tipologia contratto: Diretto con l'azienda"+

					"Durata contratto: Tempo indeterminato"+

					"I candidati ambosessi (D.lgs n. 198/2006) sono invitati a leggere l'informativa privacy ai sensi degli artt. 13 e 14 del Reg. EU 679/2016 al seguente indirizzo: https://www.gigroup.it/privacy-candidati/",
					
					"indeterminato",Education.DIPLOMA_DI_MATURITA,"indeterminato", LocalDate.of(2020,6,12),c5,jobOfferDao,companyDao);	





			JobOffer j13=createJobOffer("Lombardia", "Milano", "Milano", "CHEF DE PARTIE - SECONDI PIATTI",
		
					"Gi Group SpA, Agenzia per il Lavoro (Aut. Min. 26/11/04 Prot. n. 1101-SG) ricerca:"+

					"Per prestigioso ristorante di Milano, una figura in qualita' di CHEF DE PARTIE ? Primi Piatti."+

					"La risorsa, inserita inizialmente in somministrazione e riportando direttamente al Primo Chef ed alla Proprieta',"+
			
					"si occupera' delle seguenti mansioni:"+

					"Preparazione delle portate (circa 60 coperti)Realizzazione di piatti a base di carne, pesce e vegetariani,Collaborazione "+
			
					"con il Primo Chef per la redazione di nuovi menu',Impiattamento e presentazione delle pietanze."+

					"Il profilo ideale e' in possesso dei seguenti requisiti:"+

					"Consistente e comprovata esperienza nella mansione in qualita' di Chef De Partie, dedicato alla preparazione di secondi piatti,"+
			
					"Comprovata conoscenza delle carni, del pesce e della cucina vegetariana,Autonomia nella gestione "+
			
					"del lavoro,Forti spinte collaborative all'interno del team (la brigata e' composta da cinque persone),Flessibilita' e gestione "+
			
					"dei cambiamenti del menu',Disponibilita' a lavorare su turni spezzati (pranzo e cena),Costituira' titolo preferenziale essere in possesso di "+
			
					"Attestato HACCP in corso di validita' e Attestato Sicurezza Generica.",
				
					"determinato",Education.DIPLOMA_DI_MATURITA,"1 anno", LocalDate.of(2020,10,2),c5,jobOfferDao,companyDao);	

			JobOffer j14=createJobOffer("Lombardia", "Milano", "Milano", "RUNNER - LUXURY RESTAURANT",
		
					"Gi Group SpA, Agenzia per il Lavoro (Aut. Min. 26/11/04 Prot. n. 1101-SG) ricerca"+

					"per importante azienda cliente del settore Ristorazione di Lusso:"+

					"1 RUNNER"+

					"La risorsa si occupera' di gestire la sala con accoglienza ai clienti, servizio colazioni, pranzo, cena e servizio banqueting."+

					"Requisiti:"+

					"diploma alberghiero"+
		
					"forte motivazione per l'ambito ristorazione"+
		
					"gradita pregressa esperienza nel banqueting e/o servizi di ristorazione collettiva su grandi numeri"+

					"Completano il profilo:"+

					"una spiccata capacita' di lavorare sotto stress"+
		
					"cura dei dettagli"+
		
					"predisposizione al team working e flessibilita' nella gestione degli orari di lavoro"+
		
		 			"standing curato e ottime doti relazionali."+

					"Si offre:"+

					"Contratto part time 40h con possibilita' di proroghe con possibilita' di inserimento diretto c/o azienda cliente"+

					"Sede di lavoro: Milano centro (MI)"+

					"I candidati ambosessi (D.lgs n. 198/2006) sono invitati a leggere l'informativa privacy ai sensi degli artt. 13 e 14 del Reg."+
					
					"EU 679/2016 al seguente indirizzo:"+

					"https://www.gigroup.it/privacy-candidati/"+

					"I candidati ambosessi (D.lgs n. 198/2006) sono invitati a leggere l'informativa privacy ai sensi degli artt. 13 e 14 del Reg."+ 
		
					"EU 679/2016 al seguente indirizzo: https://www.gigroup.it/privacy-candidati/",
					
					"determinato",Education.DIPLOMA_DI_MATURITA,"1 anno", LocalDate.of(2020,3,7),c5,jobOfferDao,companyDao);	



			JobOffer j15=createJobOffer("Lombardia", "Monza", "Vimercate", "ADDETTI MENSA",
		
					"Gi Group SpA, Agenzia per il Lavoro (Aut. Min. 26/11/04 Prot. n. 1101-SG) ricerca"+

					"ADDETTI MENSA"+

					"Le principali mansioni della figura ricercata sono:eseguire operazioni tecnico?manuali di pulizia, igiene e sanificazione degli ambienti "+
		
					"e delle attrezzature/utensili;eseguire attivita' di supporto al ricevimento merci, alla produzione, al confezionamento"+

					"Il/La candidato/a ideale deve possedere esperienza, anche minima, nella mansione di Addetto/a mensa, buona manualita' e capacita' "+
		
					"di organizzazione del proprio lavoro, resistenza fisica agli sforzi e ad attivita' in piedi."+

					"Viene inoltre richiesta flessibilita' oraria per lo svolgimento di turni."+

					"Luogo di lavoro: strutture operative dell'Azienda nella citta' di Milano."+

					"Contratto: un mese piu' eventuali proroghe"+

					"I candidati ambosessi (D.lgs n. 198/2006) sono invitati a leggere l'informativa privacy ai sensi degli artt. 13 e 14 del Reg. "+
		
					"EU 679/2016 al seguente indirizzo: https://www.gigroup.it/privacy-candidati/",
					
					"a giornata",Education.DIPLOMA_DI_MATURITA,"3 mesi", LocalDate.of(2020,4,4),c5,jobOfferDao,companyDao);	







			
			
			// phase 2 : navigate data in the database
			
	
			p2=apply(p2, j1, jobOfferDao, personDao);
			p1=apply(p1, j3, jobOfferDao, personDao);
			p3=apply(p3, j1, jobOfferDao, personDao);
			p4=apply(p4, j1, jobOfferDao, personDao);
			p5=apply(p5, j1, jobOfferDao, personDao);
			p3=apply(p2, j2, jobOfferDao, personDao);
			p4=apply(p4, j2, jobOfferDao, personDao);
			p5=apply(p5, j4, jobOfferDao, personDao);
			p5=apply(p5, j5, jobOfferDao, personDao); 
						
			
			/*
			personDao.apply(p6, j1);
			 */
			//personDao.apply(p1, j1);; lancia un'eccezione perchÃ¨ non ci si puÃ² candidare due volte per la stessa offerta

			session.getTransaction().commit();
			session.beginTransaction();
			p5=unapply(p5, j5, jobOfferDao, personDao);
			session.getTransaction().commit();
			
			
			/*
			//session.beginTransaction();
			//p1=personDao.unapply(p1, j1);
			//session.getTransaction().commit();
			//DELETE JOBOFFER
			deleteJobOffer(j1,session,jobOfferDao);

			//DELETE COMPANY
			//deleteCompany(c1,session,companyDao,jobOfferDao);
	
			//DELETE USER
			deleteUser(u4,session,userDao);

			//DELETE PERSON
			deletePerson(p1,session,personDao);
			
			//DELETE CURRICULUM
			deleteCurriculum(c12,session,personDao,curriculumDao);

			//assert(personDao.findbyUserId("saviofeng@gmail.it")==p1);*/

			}
		

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}

	private static Person unapply(Person person, JobOffer joboffer, JobOfferDao jobOfferDao, PersonDao personDao) {
		Person p1=null;
		for (Person p:joboffer.getCandidancies()) {
			if (p.equals(person))
				p1=p;
		}
		joboffer.getCandidancies().remove(p1);
		joboffer=jobOfferDao.update(joboffer);
		JobOffer j1=null;
		for (JobOffer j:person.getCandidacies()) {
			if (j.equals(joboffer))
				j1=j;
		}
		person.getCandidacies().remove(j1);
		return personDao.update(person);
	}

	private static Person apply(Person p, JobOffer j, JobOfferDao jobOfferDao, PersonDao personDao) {
		j.getCandidancies().add(p);
		j=jobOfferDao.update(j);
		p.getCandidacies().add(j);
		return personDao.update(p);
	}

	private static Curriculum createCurriculum(Curriculum curriculum, CurriculumDao curriculumDao,
			PersonDao personDao) {
		Person p=curriculum.getPerson();
		p.setCurriculum(curriculum);
		curriculum=curriculumDao.create(curriculum);
		personDao.update(p);
		return curriculum;
	}

	private static JobOffer createJobOffer(String region, String province, String town, String position,
			String description, String contractType, Education minEducationLevel, String minExperience, LocalDate expiringDate, Company company,
			JobOfferDao jobOfferDao,CompanyDao companyDao) {
		JobOffer jobOffer = new JobOffer(region, province, town, position, description, contractType, minEducationLevel,
				minExperience,expiringDate, company);
		company.getJobOffers().add(jobOffer);
		jobOffer=jobOfferDao.create(jobOffer);
		company=companyDao.update(company);
		return jobOffer;
	}

	private static Person createPerson(String firstName, String secondName, LocalDate birthdate, String number, String interests,
			User user,PersonDao personDao) {
		Person person = new Person(firstName, secondName, birthdate, number, interests, user);
		user.setPerson(person);
		person=personDao.create(person);
		return person;
	}

	private static Company createCompany(String name, User user, CompanyDao companyDao) {
		Company company = new Company(name,user);
		user.setCompany(company);
		return companyDao.create(company);
		
	}

	private static void deleteJobOffer(JobOffer j, Session session, JobOfferDao jobOfferDao) {
		session.beginTransaction();
		for (Person p:j.getCandidancies()) {
			p.getCandidacies().remove(j);
		}
		j.getCandidancies().clear();
		j=jobOfferDao.update(j);
		Company company=j.getCompany();
		company.getJobOffers().remove(j);
		jobOfferDao.delete(j);
		session.getTransaction().commit();
		
	}

	private static void deleteCurriculum(Curriculum c, Session session, PersonDao personDao,
			CurriculumDao curriculumDao) {
		session.beginTransaction();
		Person p=c.getPerson();
		p.setCurriculum(null);
		p=personDao.update(p);
		c.setPerson(null);
		c=curriculumDao.update(c);
		curriculumDao.delete(c);
		session.getTransaction().commit();
		
	}

	private static void deletePerson(Person p, Session session, PersonDao personDao) {
		session.beginTransaction();
		for (JobOffer j:p.getCandidacies()) {
			j.getCandidancies().remove(p);
		};
		p.getCandidacies().clear();
		personDao.delete(p);
		session.getTransaction().commit();
		
	}

	private static void deleteUser(User u, Session session, UserDao userDao) {
		
		session.beginTransaction();
		u.getRoles().clear();
		userDao.update(u);
		userDao.delete(u);
		session.getTransaction().commit();
		
	}

	private static void deleteCompany(Company c, Session session, CompanyDao companyDao, JobOfferDao jobOfferDao) {
		
		session.beginTransaction();
		for (JobOffer j:c.getJobOffers()) {
			jobOfferDao.delete(j);
		}
		c.getJobOffers().clear();
		c=companyDao.update(c);
		companyDao.delete(c); 
		session.getTransaction().commit();
		
	}
	

}
