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

import jobworld.utils.LocalDateAttributeConverter;

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
	private long id;
	private String region;
	private String province;
	private String town;
	private String position;
	private String description;
	private String contractType;
	private String minEducationLevel;
	private String minExperience;
	private int interested;
	private Set<Person> candidancies = new HashSet<Person>();
	private Company company;
	private LocalDate publicationDate;

	public JobOffer() {
		super();
	}

	public JobOffer(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, Company company) {
		super();
		this.region = region;
		this.province = province;
		this.town = town;
		this.position = position;
		this.description = description;
		this.contractType = contractType;
		this.minEducationLevel = minEducationLevel;
		this.minExperience = minExperience;
		this.company = company;
		this.publicationDate = LocalDate.now();
		this.interested = 0;
	}

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

	public String getMinEducationLevel() {
		return minEducationLevel;
	}

	public void setMinEducationLevel(String minEducationLevel) {
		this.minEducationLevel = minEducationLevel;
	}

	public String getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}

	public int getInterested() {
		return interested;
	}

	public void setInterested(int interested) {
		this.interested = interested;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidacies", joinColumns = @JoinColumn(name = "JOB_OFFER_ID"), inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
	public Set<Person> getCandidancies() {
		return candidancies;
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

	// aggiorna la lista delle persone candidate per quel lavoro e il numero degli
	// interessati ovviamente
	public void applying(Person p1) {
		candidancies.add(p1);
		interested++;
	}

	@Override
	public String toString() {
		return "JobOffer [id=" + id + ", region=" + region + ", province=" + province + ", town=" + town + ", position="
				+ position + ", description=" + description + ", contractType=" + contractType + ", minEducationLevel="
				+ minEducationLevel + ", minExperience=" + minExperience + ", interested=" + interested
				+ ", number of candidancies=" + candidancies.size() + ", company=" + company.getId()
				+ ", publicationDate=" + publicationDate + "]";
	}

	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

}