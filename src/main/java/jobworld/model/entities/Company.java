package jobworld.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * La classe Company rappresenta un Azienda 
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class Company {

	private long id;
	private Set<JobOffer> jobOffers = new HashSet<JobOffer>();

	@NotBlank
	private String name;
	private User user;

	public Company() {
		super();
	}

	public Company(String name, User user) {
		this.name = name;
		this.user = user;
	}

	/**
	 * Se rimuoviamo una compagnia rimuoviamo tutte le sue offerte di lavoro con un
	 * meccanismo a cascata
	 */

	/**
	 * Metodi setters/getters e definizione delle tabelle con le relative relazioni
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(Set<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Company [jobOffers=" + jobOffers + "]";
	}

}
