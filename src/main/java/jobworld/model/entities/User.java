package jobworld.model.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import jobworld.model.entities.Role;





/**
 * Classe User rappresentante un Utente ed i suoi attributi/metodi.
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
	@Email(message="Email not well formed")
	private String email;
	private String password;
	private String description;
	private String image;
	private Company company;
	private Person person;
	private Set<Role> roles = new HashSet<Role>();

	public User() {
	}
	
	
	/**
	 * Metodo che crea un nuovo utente
	 * 
	 * @param email       email dell'utente
	 * @param password    password dell'account dell'utente
	 * @param description descrizione dell'utente che può essere sia un utente che un azienda
	 * @param image       path dell'immagine utente: logo per aziende e foto per utenti
	 */
	
	public User(String email, String password, String description, String image) {
		super();
		this.email = email;
		this.password = password;
		this.description = description;
		this.image = image;
		
	}

	/**
	 	Metodi get/set dell'email-password-description-image
	 */	
	
	@Id
	@Column(unique = true)
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

	@Column(length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(unique=true)
	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(length = 1000)
	public String getImage() {
		return image;
	}

	/**
	 * Definizione della relazione uno a uno tra User e Company
	 * 
	 */

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Definizione della relazione uno a uno tra User e Person
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	public Person getPerson() {
		return person;
	}

	
	public void setPerson(Person person) {
		this.person = person;
	}


	public void addRole(Role role) {
		  if (this.roles == null) {
			  this.roles = new HashSet<Role>();
		  }		  
		  this.roles.add(role);
	}
	  
	public void setRoles(Set<Role> roles) {
		  this.roles = roles;
	}
	  /**
	   * Definizione della relazione molti a molti tra User e Role
	   * 
	   */
	@ManyToMany
	@JoinTable( name = "users", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"), 
		        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
	public Set<Role> getRoles() {
		  return this.roles;
    }
		 
	  //da valutare se serve
	public Set<Role> roles () {
		  return this.roles;
	}
	 
	 
	 
	 
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ","
				+ " description=" + description+ ", image=" + image + ", company=" + 
				company + ", person=" + person + "]";
	}	
	
}
