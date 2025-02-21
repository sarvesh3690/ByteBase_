package com.bytebase.service;

import java.util.List;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ModuleCoordinatorDTO;
import com.bytebase.dto.ModuleCoordinatorRespDTO;
import com.bytebase.entities.ModuleCoordinator;

public interface ModuleCoordinatorService {
	
	ApiResponse addModuleCoordinator(ModuleCoordinatorDTO coordinator);
	
	ApiResponse deleteModuleCoordinator(Long id);

	List<ModuleCoordinatorRespDTO> getAllModuleCoordinators();
	
	ApiResponse updateModuleCoordinator(Long id, ModuleCoordinator coordinator);

	String getModuleCoordinator(String email);

	void verifyOtp(String email, String otp);

	ModuleCoordinatorRespDTO addPassWord(String email, String password);

}
