package model;

import java.util.ArrayList;

import javax.persistence.Entity;
/**
 * Classe rappresentante una Azienda ed i suoi attributi/metodi.
 */
@Entity
public class Company extends User {
	
	private ArrayList <JobOffer> jobOffers;
	
	public Company(String email, String password, String description, ArrayList<JobOffer> jobOffers) {
		super(email, password, description);
		this.jobOffers = jobOffers;
	}

	public ArrayList<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(ArrayList<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	
	

}
