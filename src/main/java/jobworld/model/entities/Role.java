package jobworld.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Role {
	
	    public enum TypeRole{ADMIN,USER,COMPANY};
	    
	    private TypeRole name;
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setName(TypeRole name) {
			this.name=name;
		}

		public TypeRole getName() {
			return name;
		}	    
		
		

	   /**
	    * Metodo get/set di name e id
	    * 
	    */

}
