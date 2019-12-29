package jobworld.model.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import jobworld.utils.LocalDateAttributeConverter;
import jobworld.utils.TimestampAttributeConverter;

/**
 * Classe JobOffer rappresentante una Offerta di Lavoro ed i suoi
 * attributi/metodi.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */



@Entity
public class JobOffer {
	public enum Education{LAUREA_SPECIALISTICA,LAUREA_TRIENNALE,DIPLOMA_DI_MATURITA,LICENZA_MEDIA,SENZA_STUDI};
	private long id;
	private String region;
	private String province;
	private String town;
	private String position;
	private String description;
	private String contractType;
	private Education minEducationLevel;
	private String minExperience;
	private Set<Person> candidancies = new HashSet<Person>();
	private Company company;
	private long publicationDate;
	private LocalDate expiringDate;
	private int version;

	public JobOffer() {
		super();
	}

	
	public JobOffer(String region, String province, String town, String position, String description,
			String contractType, Education minEducationLevel, String minExperience,LocalDate expiringDate,
			Company company) {
		this.region = region;
		this.province = province;
		this.town = town;
		this.position = position;
		this.description = description;
		this.contractType = contractType;
		this.minEducationLevel = minEducationLevel;
		this.minExperience = minExperience;
		this.company = company;
		this.publicationDate = System.currentTimeMillis();
		this.expiringDate=expiringDate;
	}




	/**
	* Metodi setters/getters e definizione delle tabelle con le relative relazioni
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOB_OFFER_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}


	public Education getMinEducationLevel() {
		return minEducationLevel;
	}


	public void setMinEducationLevel(Education minEducationLevel) {
		this.minEducationLevel = minEducationLevel;
	}


	public String getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}


	//campo data candidatura
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidacies", joinColumns = @JoinColumn(name = "JOB_OFFER_ID"), inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
	public Set<Person> getCandidancies() {
		return this.candidancies;
	}

	public void setCandidancies(Set<Person> candidancies) {
		this.candidancies = candidancies;
	}

	@ManyToOne
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
		return "JobOffer [id=" + id + ", region=" + region + ", province=" + province + ", town=" + town + ", position="
				+ position + ", description=" + description + ", contractType=" + contractType + ", minEducationLevel="
				+ minEducationLevel + ", minExperience=" + minExperience 
				+ ", number of candidancies=" + candidancies.size() + ", company=" + company.getId()
				+ ", publicationDate=" + publicationDate + "]";
	}

	@Convert(converter = TimestampAttributeConverter.class)
	public Long getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Long publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate getExpiringDate() {
		return expiringDate;
	}


	public void setExpiringDate(LocalDate expiringDate) {
		this.expiringDate = expiringDate;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof JobOffer) {
			if (this.getId()==((JobOffer) obj).getId())
				{
				return true;
						}
			else return false;
				}
		return super.equals(obj);
	}



}