package jobworld.model.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
	private String email;
	private String password;//fare cifratura, nel database no password in chiaro
	private String description;
	private String image;
	private Company company;
	private Person person;

	/**
	 * crea un nuovo utente
	 * 
	 * @param email       email dell'utente
	 * @param password    password dell'account dell'utente
	 * @param description descrizione dell'utente (che sia descrizione dell'azienda
	 *                    o della persona)
	 * @param image       path dell'immagine utente: logo per aziende e foto per utenti
	 */
	public User() {
	}


	public User(String email, String password, String description, String image) {
		super();
		this.email = email;
		this.password = password;
		this.description = description;
		this.image = image;
		
	}




	/**
	* Metodi setters/getters e definizione delle tabelle con le relative relazioni
	 */	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
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



	@OneToOne(mappedBy = "user")
	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}


	@OneToOne(mappedBy = "user")
	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}


	 @ManyToMany
	  @JoinTable( name = "users_roles", joinColumns = @JoinColumn(name = "email", 
	  referencedColumnName = "email"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
	  private Set<Role> roles = new HashSet<Role>();
	
	 public Set<Role> roles () {
		  return this.roles;
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
	  
	  public Set<Role> getRoles() {
		  return this.roles;
	  }
	 
	 
	 
	 
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", description=" + description
				+ ", image=" + image + ", company=" + company + ", person=" + person + "]";
	}

	
	
	
	
	
}
