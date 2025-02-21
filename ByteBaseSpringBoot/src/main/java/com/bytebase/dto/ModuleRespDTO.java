package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRespDTO extends BaseDTO {
		private String name;
		private Long facultyId;
		private String facultyName;
}
