package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecordingDTO extends BaseDTO {
	
	 private String topic;
//	    private String facultyName;
	 private String link;
	 
	 private Long moduleId;
	 
	 private Long facultyId;

	    
//	    private Module module;

}
