package model.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import utils.LocalDateAttributeConverter;

/**
 * Classe entità rappresentante una Persona ed i suoi attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "PERSON_ID"))
public class Person extends User {
	private String firstName;
	private String secondName;
	private LocalDate birthDate;
	private String number;
	private Curriculum curriculum;
	private String interests;
	private Set<JobOffer> candidacies = new HashSet<JobOffer>();

	public Person() {
		super();
	}

	public Person(String email, String password, String description, String firstName, String secondName,
			LocalDate birthDate, String number, String interests) {
		super(email, password, description);
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.number = number;
		this.interests = interests;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "TELEPHONE_NUMBER")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@OneToOne(mappedBy = "person")
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidacies", joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "JOB_OFFER_ID"))
	public Set<JobOffer> getCandidacies() {
		return candidacies;
	}

	public void setCandidacies(Set<JobOffer> candidacies) {
		this.candidacies = candidacies;
	}

	// aggiunge quell'offerta di lavoro alla lista delle candidature
	public void apply(JobOffer jobOffer) {
		candidacies.add(jobOffer);
		jobOffer.applying(this);
	}

}
