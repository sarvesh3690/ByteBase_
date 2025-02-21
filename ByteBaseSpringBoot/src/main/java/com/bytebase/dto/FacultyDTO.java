package com.bytebase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FacultyDTO extends BaseDTO{
	private String name;
    private String email;
}
