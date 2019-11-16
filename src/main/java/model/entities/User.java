package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe entit� rappresentante un Utente ed i suoi attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class User {
	private long id;
	private String email;
	private String password;
	private String description;

	/**
	 * crea un nuovo utente
	 * 
	 * @param email       email dell'utente
	 * @param password    password dell'account dell'utente
	 * @param description descrizione dell'utente (che sia descrizione dell'azienda
	 *                    o della persona)
	 */
	public User(String email, String password, String description) {
		super();
		this.email = email;
		this.password = password;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
