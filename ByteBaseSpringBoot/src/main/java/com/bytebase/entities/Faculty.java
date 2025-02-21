package com.bytebase.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "faculty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Faculty extends BaseEntity{

	
	 private String name;
	    private String email;
	    private String password;

	    @OneToMany(mappedBy = "teacher")
	    private List<Module> modules = new ArrayList<>();
	    
	    
	    public void addModule(Module module) {
	    	this.modules.add(module);
	    	module.setTeacher(this);
	    }
	    
	    public void removeModule(Module module) {
	    	this.modules.remove(module);
	    	module.setTeacher(null);
	    }
}
