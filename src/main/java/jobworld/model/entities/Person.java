package jobworld.model.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import jobworld.utils.LocalDateAttributeConverter;

/**
 * Classe Person rappresentante una Persona ed i suoi attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class Person  {
	

	private long id;
	@NotNull
	private String firstName;
	@NotNull
	private String secondName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message="la data di nascita deve essere antecedente a quella attuale!")
	private LocalDate birthDate;
	private String number;
	private Curriculum curriculum;
	private String interests;
	private Set<JobOffer> candidacies = new HashSet<JobOffer>();
	private User user;

	public Person() {
		super();
	}
	
	public Person(String firstName, String secondName, LocalDate birthDate, String number,
			String interests, User user) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.number = number;
		this.interests = interests;
		this.user=user;
	}

	/**
	* Metodi set/get più la definizione delle colonne 
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	/**
	 * Definizione della relazione uno a uno tra Person e Curriculum
	 * @return curriculum
	 */
	@OneToOne(mappedBy = "person", cascade=CascadeType.ALL)
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
	
	/**
	 * Definizione della relazione molti a molti tra Person e JobOffer
	 * 
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "candidancies")
	public Set<JobOffer> getCandidacies() {
		return this.candidacies;
	}

	public void setCandidacies(Set<JobOffer> candidacies) {
		this.candidacies = candidacies;
	}
	
	/**
	 * Definizione della relazione uno a uno tra User e Person
	 * 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isInterested(JobOffer jobOffer) {
		for (JobOffer j:candidacies) {
			if(j.getId() == jobOffer.getId()) {
				return true;
			}
		};
		return false;
	}

	/**
	 * Metodo per la stampa
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", birthDate="
				+ birthDate + ", number=" + number + ", curriculum=" + curriculum + ", interests=" + interests
				+ ", candidacies=" + candidacies + ", user=" + user + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			if (this.getId()==((Person) obj).getId())
				{
				return true;
						}
			else return false;
				}
		return super.equals(obj);
	}
	

}
