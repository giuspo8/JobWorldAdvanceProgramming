package model.entities;


import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe entità rappresentante una Azienda ed i suoi attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class Company extends User {

	private ArrayList<JobOffer> jobOffers;

	public Company(String email, String password, String description) {
		super(email, password, description);
	}

	/**
	 * Se rimuoviamo una compagnia rimuoviamo tutte le sue offerte di lavoro con un
	 * meccanismo a cascata
	 */
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	public ArrayList<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(ArrayList<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

}
