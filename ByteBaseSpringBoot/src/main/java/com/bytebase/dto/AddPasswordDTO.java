package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPasswordDTO {
	private String email;
	private String password;
}
