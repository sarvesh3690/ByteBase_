package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRespDTO extends BaseDTO {
	
	
	
    private String prn;

    private String name;

  
    private String email;
    
    private String phone;
    
    private String role="student";

 
}
