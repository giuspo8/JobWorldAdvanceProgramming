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
	// added to allow a single thread session sharing scheme
	this.session = session; 
}

	public Session setSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session getSession() {
		// 1. in case a shared session exists, return it (e.g. data generation script)
		Session session = this.session;
		if (session == null) {
			// 2. otherwise generate a new session using the factory (e.g. Spring MVC)
			session = this.sessionFactory.getCurrentSession();
		}
		return session;
	}
}
