package jobworld.model.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Role {
	
	    private String name;
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;	    
	    
	   /* @ManyToMany
		  @JoinTable( name = "users", joinColumns = @JoinColumn(name = "USER_ID", 
		  referencedColumnName = "id"), 
		  inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "idd")) 
	    public Set<Role> roles = new HashSet<Role>();*/
	   /**
	    * Metodo get/set di name e id
	    * 
	    */
	    public String getName() {
	    	return this.name;
	    }
	    
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    public Long getId() {
	    	return this.id;
	    }
	    
	    public void setId(Long id) {
	    	this.id = id;
	    }
}
