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

import com.bytebase.dto.ModuleDTO;
import com.bytebase.service.ModuleService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping
	public ResponseEntity<?> getAllCourseCoordinator(){
		if(moduleService.getAllModules().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.ok(moduleService.getAllModules());
	}
	
	@PostMapping("/addmodule")
	public ResponseEntity<?> addNewModuleCoordinator(@RequestBody ModuleDTO moduleCoordintor){
		return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.addModule(moduleCoordintor));
	}
	
	
	
	
}
