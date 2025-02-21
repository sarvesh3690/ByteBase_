package com.bytebase.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmailDetails {

	private String recipient;
	private String msgBody;
	private String subject;
	
}
