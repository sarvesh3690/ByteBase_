package com.bytebase.service;

import java.util.List;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.FacultyDTO;
import com.bytebase.dto.FacultyRespDTO;

public interface FacultyService {
	
	ApiResponse addFaculty(FacultyDTO coordinator);
	
	List<FacultyRespDTO> getAllFaculty();
	
	

}
