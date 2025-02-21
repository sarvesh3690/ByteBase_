package com.bytebase.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AssignmentDTO extends BaseDTO {

	private String title;
	private Date deadline;
	

	private Long moduleId; //
}
