package model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Person extends User {
	private String firstName;
	private String secondName;
	private Date birthDate;
	private String number;
	private String curriculum;
	private String interests;
	
	public Person(String email, String password, String description, String firstName, String secondName,
			Date birthDate, String number, String curriculum, String interests) {
		super(email, password, description);
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.number = number;
		this.curriculum = curriculum;
		this.interests=interests;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	

}
