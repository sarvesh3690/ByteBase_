package com.bytebase.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.AssignmentDTO;
import com.bytebase.dto.AssignmentUploadDTO;
import com.bytebase.service.AssignmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/assignment")
public class AssignmentController {
	
	@Autowired
	private AssignmentService classWorkService;
	
	@GetMapping
	public ResponseEntity<?> getAllClassWork(){
		if(classWorkService.getAssignments().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.ok(classWorkService.getAssignments());
	}
	
	@PostMapping("/addassignment")
	public ResponseEntity<?> addClassWorks(@RequestPart MultipartFile file, @RequestParam String classWork){
		
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			AssignmentDTO classWork1 =
			 mapper.readValue(classWork, AssignmentDTO.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(classWorkService.addAssignemt(file, classWork1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!!"));
	}
	
	@PostMapping("/uploadassignment")
	public ResponseEntity<?> uploadAssignment(@RequestPart MultipartFile file, @RequestParam String assignmentId){
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			AssignmentUploadDTO classWork1 =
			 mapper.readValue(assignmentId, AssignmentUploadDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(classWorkService.uploadAssignment(file, classWork1));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!!"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUploadAssignments(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(classWorkService.getUploadAssignment(id));
	}
	
	
	
	
	
}
