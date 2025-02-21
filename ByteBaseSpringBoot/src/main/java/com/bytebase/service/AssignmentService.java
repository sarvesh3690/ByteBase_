package com.bytebase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.AssignmentDTO;
import com.bytebase.dto.AssignmentNoRespDTO;
import com.bytebase.dto.AssignmentRespDTO;
import com.bytebase.dto.AssignmentUploadDTO;

public interface AssignmentService {
	
	ApiResponse addAssignemt( MultipartFile file, AssignmentDTO classWork) throws IOException;
	


	List<AssignmentRespDTO> getAssignments();



	ApiResponse uploadAssignment(MultipartFile file, AssignmentUploadDTO classWork1) throws IOException;



	AssignmentNoRespDTO getUploadAssignment(Long id);
	
	
}
