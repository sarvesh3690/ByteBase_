package com.bytebase.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentRespDTO extends BaseDTO {
	private String pdfFile;
	private String title;
	private Date deadline;
	private String moduleName;
}
