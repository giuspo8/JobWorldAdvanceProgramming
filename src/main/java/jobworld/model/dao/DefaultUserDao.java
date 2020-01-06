package jobworld.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jobworld.model.entities.User;

@Transactional
@Repository("userDao") 
public class DefaultUserDao extends DefaultDao implements UserDao {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Metodo che sovrascrive il create dell'user
	 * @param email  l'email
	 * @param password  la password
	 * @param description  la descrizione
	 * @param image  l'immagine
	 * 
	 * @return user
	 */
	@Override
	@Transactional
	public User create(String email, String password, String description, String image) {
		User user = new User(email, password, description, image);
		this.getSession().save(user);
		return user;
	}
	/**
	 * Metodo che sovrascrivel'update dell'user
	 * @param user l'utente
	 * 
	 * @return merged
	 */
	@Override
	@Transactional
	public User update(User user) {
		User merged = (User) this.getSession().merge(user);
		return merged;
	}
	/**
	 * Metodo che sovrascrive il delete dell'user
	 * @param user l'utente
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void delete(User user) {
		this.getSession().delete(user);
	}
	/**
	 * Metodo che sovrascrive il findByEmail dell'user, dall'email restituisce l'utente
	 * @param email  l'email
	 * 
	 * @return user
	 */
	@Override
	@Transactional
	public User findByEmail(String email) {
		return getSession().find(User.class, email);
	}
	/**
	 * Metodo che sovrascrive il findAll dell'user
	 * 
	 * @return tutti gli utenti
	 */
	@Override
	@Transactional
	public List<User> findAll() {
		return getSession().createQuery("from User u", User.class).getResultList();
	}

	/**
	 * Metodo che sovrascrive l'encryptPassword dell'user
	 * @param password la password
	 * 
	 * @return la password cifrata
	 */
	@Override
	public String encryptPassword(String password) {
		return this.passwordEncoder.encode(password);
	}
	
	
	/**
	 * Metodo che sovrascrive il finByMailandPassword dell'user
	 * @param email  l'email
	 * @param password la password
	 * 
	 * @return dall'email e la password ritorna l'utente
	 */
	@Override
	@Transactional
	public User findByMailandPassword(String email, String password) {
		return getSession().createQuery("from User u where u.email=:email and u.password=:password", User.class)
				.setParameter("email", email).setParameter("password",password).getSingleResult();
	}

}
