package com.bytebase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytebase.dto.FacultyDTO;
import com.bytebase.service.FacultyService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@GetMapping
	public ResponseEntity<?> getAllCourseCoordinator(){
		if(facultyService.getAllFaculty().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.ok(facultyService.getAllFaculty());
	}
	
	@PostMapping("/addfaculty")
	public ResponseEntity<?> addNewModuleCoordinator(@RequestBody FacultyDTO moduleCoordintor){
		return ResponseEntity.status(HttpStatus.CREATED).body(facultyService.addFaculty(moduleCoordintor));
	}
	
	
	
	
}
