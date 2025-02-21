package com.bytebase.service;

import com.bytebase.dto.AdminRespDTO;

public interface AdminService {
	
	void verifyAdmin(String email);
	AdminRespDTO verifyPassword(String Password);

}
