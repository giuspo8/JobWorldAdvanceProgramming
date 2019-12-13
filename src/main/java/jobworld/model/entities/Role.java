package jobworld.model.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
public class Role {
	 private String name;
	 private Long idd;
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="ROLE_ID")
	    
	   
	    
	    
	    @ManyToMany
		  @JoinTable( name = "users", joinColumns = @JoinColumn(name = "USER_ID", 
		  referencedColumnName = "id"), 
		  inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "idd")) 
	    public Set<Role> roles = new HashSet<Role>();
	   
	    public String getName() {
	    	return this.name;
	    }
	    
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    public Long getId() {
	    	return this.idd;
	    }
	    
	    public void setId(Long id) {
	    	this.idd = idd;
	    }
}
