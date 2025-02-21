package com.bytebase.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ApiResponse {

	private String message;
	private LocalDate respTime;
	
	public ApiResponse(String message) {
		this.message = message;
		this.respTime = LocalDate.now();
	}
}
