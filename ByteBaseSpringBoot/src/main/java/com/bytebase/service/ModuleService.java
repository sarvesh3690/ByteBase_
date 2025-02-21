package com.bytebase.service;

import java.util.List;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ModuleDTO;
import com.bytebase.dto.ModuleRespDTO;

public interface ModuleService {
	
	ApiResponse addModule(ModuleDTO module);
	
	List<ModuleRespDTO> getAllModules();

}
