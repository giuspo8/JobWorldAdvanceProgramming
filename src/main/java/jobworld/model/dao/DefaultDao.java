package jobworld.model.dao;
/**
 * Classe DefaultDao
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */


import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public abstract class DefaultDao {

	private SessionFactory sessionFactory;
	private Session session;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

@Resource(name = "sessionFactory")
public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
public void setSession(Session session) {
	// aggiunto per permettere a una singola sessione di essere condivisa
	this.session = session; 
}

	public Session getSession() {
		// 1. nel caso esista una sessione condivisa, la ritorna (e.g. data generation script)
		Session session = this.session;
		if (session == null) {
			// 2. altrimenti genera una nuova sessione utilizzando la factory (e.g. Spring MVC)
			session = this.sessionFactory.getCurrentSession();
		}
		return session;
	}
}
