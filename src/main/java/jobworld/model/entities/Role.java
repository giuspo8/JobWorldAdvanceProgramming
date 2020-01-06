package jobworld.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * La classe Role rappresenta il ruolo di un utente
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Entity
public class Role {

	public enum TypeRole {
		ADMIN, USER, COMPANY
	};

	private TypeRole name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Metodo get/set di name e id
	 * 
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(TypeRole name) {
		this.name = name;
	}

	public TypeRole getName() {
		return name;
	}

	

}
