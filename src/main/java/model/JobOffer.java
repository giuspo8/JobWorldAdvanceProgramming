package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * Classe rappresentante una Offerta di Lavoro ed i suoi attributi/metodi.
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
	private int interested=0;
	private Set<Person> candidancies= new HashSet<Person>();
	private Company company;
	
	public JobOffer(String region, String province, String town, String position, String description,
			String contractType, String minEducationLevel, String minExperience, int interested, Set<Person> candidancies,
			Company company) {
		super();
		this.region = region;
		this.province = province;
		this.town = town;
		this.position = position;
		this.description = description;
		this.contractType = contractType;
		this.minEducationLevel = minEducationLevel;
		this.minExperience = minExperience;
		this.interested = interested;
		this.candidancies=candidancies;
		this.company=company;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Set<Person> getCandidancies() {
		return candidancies;
	}

	public void setCandidancies(Set<Person> candidancies) {
		this.candidancies = candidancies;
	}

	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)		
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	
}