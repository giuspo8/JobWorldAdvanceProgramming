# JobWorldAdvanceProgramming
Progetto di Programmazione Avanzata  
Si vuole progettare un’applicazione web per la gestione di un “sistema per l’impiego” rivolto ad utenti disoccupati/occupati ed aziende in cerca di personale.
L’applicazione deve **fornire/disporre** di alcune funzionalità/sezioni essenziali:  
* **sezione** regole d’uso e caratteristiche del servizio;
* **sezione** “cerca lavoro”, pagina principale del sito. Qui vengono mostrate le offerte di lavoro ed è possibile effettuare ricerche sulle aziende iscritte al sito e le offerte di lavoro disponibili, le quali sono filtrabili in base al luogo, il titolo di studio richiesto, l’esperienza richiesta, lo stipendio offerto e la disponibilità ad eventuali trasferte;
* vi sono 3 tipologie di utenti:
**utente standard**: può visionare la sezione “cerca lavoro” ed effettuare l’operazione di registrazione al sito, o login se già iscritto precedentemente, e passare quindi allo stato di “utente registrato”;  
**utente registrato**: dispone delle stesse operazioni dell’”utente standard”, oltre che di operazioni CRUD sui suoi dati, tra cui le informazioni generali (nome, cognome, contatti, ecc.), il curriculum, ecc. Ogni utente può dichiarare il proprio interesse per un’offerta di lavoro, ed effettuare l’operazione di logout per passare allo stato di “utente standard”;  
**utente azienda**: dispone delle stesse operazioni dell’”utente standard”, oltre che di operazioni CRUD sui dati aziendali, tra cui le informazioni generali (nome, sede, ecc.), le informazioni di contatto, il settore, ecc. L’azienda può pubblicare le sue offerte di lavoro in una sezione “inserisci offerta di lavoro”, e può controllare ogni interesse in una propria offerta pubblicata. Infine può visualizzare la lista degli utenti che si sono mostrati interessati per ogni propria offerta di lavoro e può contattarli. Effettuando l’operazione di logout l’”utente azienda” passa allo stato di utente standard;
Inoltre si vuole fornire la documentazione Javadoc associata al progetto.

# Istruzioni d'uso
1) Una volta clonato il progetto andare su src/main/resources e modificare i file db.config.properties e path.properties con i dati del proprio database e del path dove è presente la cartella img del progetto nel vostro pc.
2) aprire la classe jobworld.test.loadData e lanciarla con Run as--> Java Application
3) lanciare il progetto per intero come Run as--->Run on Server utilizzando un server (per esempio TomCat dopo averlo configurato)
4) All'atto dell'eventuale aggiunta di immagini al proprio profilo di utente o azienda fare refresh (F5) del progetto su eclipse o altro Ide per vedere aggiornare l'immagine del proprio profilo

**NB** l'applicativo è in grado di riconoscere dove si trova la persona connessa a seconda del suo ip e compilare di conseguenza la sezione di ricerca. Trattandosi di un'applicazione a livello accademico che viene lanciata in locale in questo caso per provare diverse zone d'Italia è necessario modificare sulla classe JobWorld.controller.HomeController la stringa String ip assegnandoli l'Ip della zona desiderata.
