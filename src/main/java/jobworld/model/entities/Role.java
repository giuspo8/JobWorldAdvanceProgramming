package jobworld.model.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Role {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    @Column
	    private String name;
	    
	    
	   @ManyToMany(mappedBy = "roles")
	   private Collection <User> users;
	   
	   
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
