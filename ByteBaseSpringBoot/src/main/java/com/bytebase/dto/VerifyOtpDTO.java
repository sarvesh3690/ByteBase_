package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpDTO {
	private String email;
	private String otp;
}
