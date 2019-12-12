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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session setSession(Session session) {
		if (session==null) 
		{
			return sessionFactory.getCurrentSession();//da rivedere
		}
		return session;
	}
}
