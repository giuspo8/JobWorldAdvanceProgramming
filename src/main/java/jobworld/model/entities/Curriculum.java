package jobworld.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;


/**
 * Classe Curriculum rappresentante il curriculum collegato ad una persona.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class Curriculum {
	private long id;
	private Person person;
	private String workExperience;
	private String education;
	private String personalSkills;
	private String additionalInfo;
	private int version;

	public Curriculum() {
		super();
	}

	public Curriculum(Person person) {
		this.person = person;
	}

	public Curriculum(Person person, String workExperience, String education, String personalSkills,
			String additionalInfo) {
		super();
		this.person = person;
		this.workExperience = workExperience;
		this.education = education;
		this.personalSkills = personalSkills;
		this.additionalInfo = additionalInfo;
	}

	/**
	*Metodi setters/getters e definizione delle tabelle con le relative relazioni
	 */	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(length = 1000)
	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	@Column(length = 1000)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(length = 1000)
	public String getPersonalSkills() {
		return personalSkills;
	}

	public void setPersonalSkills(String personalSkills) {
		this.personalSkills = personalSkills;
	}

	@Column(length = 1000)
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}
	

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", personName=" + person.getFirstName() + " " + person.getSecondName()
				+ ", workExperience=" + workExperience + ", education=" + education + ", personalSkills="
				+ personalSkills + ", additionalInfo=" + additionalInfo + "]";
	}

}
