package jobworld.model.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	private String firstName;
	private String secondName;
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
			String interests) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.number = number;
		this.interests = interests;
	}



	/**
	* Metodi setters/getters e definizione delle tabelle con le relative relazioni
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

	@OneToOne(mappedBy = "person")
	@OnDelete(action = OnDeleteAction.CASCADE)
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
	@JoinTable(name = "candidacies", joinColumns = @JoinColumn(name = "PERSON_ID", updatable=false), inverseJoinColumns = @JoinColumn(name = "JOB_OFFER_ID", updatable=false))
	public Set<JobOffer> getCandidacies() {
		return candidacies;
	}

	public void setCandidacies(Set<JobOffer> candidacies) {
		this.candidacies = candidacies;
	}

	@OneToOne(mappedBy = "person")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	// aggiunge quell'offerta di lavoro alla lista delle candidature
	public void apply(JobOffer jobOffer) {
		candidacies.add(jobOffer);
	}

}
