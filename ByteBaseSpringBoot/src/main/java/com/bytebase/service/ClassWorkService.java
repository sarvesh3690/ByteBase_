package com.bytebase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ClassWorkDTO;
import com.bytebase.dto.ClassWorkRespDTO;
import com.bytebase.entities.ClassWork;

public interface ClassWorkService {
	
	ApiResponse addClassWork( MultipartFile file, ClassWorkDTO classWork) throws IOException;
	
	ApiResponse deleteClassWork(Long id);

	List<ClassWorkRespDTO> getAllClassWork();
	
	ApiResponse updateClassWork(Long id, ClassWork classWork);

}
