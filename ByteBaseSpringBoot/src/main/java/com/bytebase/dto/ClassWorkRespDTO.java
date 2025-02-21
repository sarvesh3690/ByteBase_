package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class ClassWorkRespDTO extends BaseDTO {
	private String zipFile;

	private String module;

	private String teacher;
	
	private String topic;
}
