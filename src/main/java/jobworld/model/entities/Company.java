package jobworld.model.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe Company rappresentante una Azienda ed i suoi attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "COMPANY_ID"))
public class Company extends User {

	private Set<JobOffer> jobOffers = new HashSet<JobOffer>();
	private String name;

	public Company() {
		super();
	}

	public Company(String name, String email, String password, String description, String image, Boolean roleAdmin) {
		super(email, password, description, image, roleAdmin);
		this.name=name;
	}

	/**
	 * Se rimuoviamo una compagnia rimuoviamo tutte le sue offerte di lavoro con un
	 * meccanismo a cascata
	 */

	
	/**
	*Metodi setters/getters e definizione delle tabelle con le relative relazioni
	 */	
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

	@Override
	public String toString() {
		return "Company [jobOffers=" + jobOffers + "]";
	}

}
