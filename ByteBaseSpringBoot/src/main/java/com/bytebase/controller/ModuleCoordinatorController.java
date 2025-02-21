package com.bytebase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytebase.dto.AddPasswordDTO;
import com.bytebase.dto.AuthenticateEmailDTO;
import com.bytebase.dto.ModuleCoordinatorDTO;
import com.bytebase.dto.VerifyOtpDTO;
import com.bytebase.entities.ModuleCoordinator;
import com.bytebase.service.ModuleCoordinatorService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/modulecoordinator")
public class ModuleCoordinatorController {
	
	@Autowired
	private ModuleCoordinatorService moduleCoordinatorService;
	
	@GetMapping
	public ResponseEntity<?> getAllCourseCoordinator(){
		if(moduleCoordinatorService.getAllModuleCoordinators().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.ok(moduleCoordinatorService.getAllModuleCoordinators());
	}
	
	@PostMapping("/addmodulecoordinator")
	public ResponseEntity<?> addNewModuleCoordinator(@RequestBody ModuleCoordinatorDTO moduleCoordintor){
		return ResponseEntity.status(HttpStatus.CREATED).body(moduleCoordinatorService.addModuleCoordinator(moduleCoordintor));
	}
	
	@DeleteMapping("/deletemodulecoordinator/{id}")
	public ResponseEntity<?> deleteModuleCoordinator(@PathVariable Long id){
		return ResponseEntity.ok(moduleCoordinatorService.deleteModuleCoordinator(id));
	}
	
	@PutMapping("updatemodulecoordinator/{id}")
	public ResponseEntity<?> updateModuleCoordinator(@PathVariable Long id, @RequestBody ModuleCoordinator ModuleCoordinator) {
		return ResponseEntity.ok(moduleCoordinatorService.updateModuleCoordinator(id, ModuleCoordinator));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> Authentication(@RequestBody AuthenticateEmailDTO email) {
		String message = moduleCoordinatorService.getModuleCoordinator(email.getEmail());
		if (message.equals("add password"))
			return ResponseEntity.status(HttpStatus.OK).body("Student Found");
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).body("Student Found");
			
	}

	@PostMapping("/verifyotp")
	public ResponseEntity<?> verify(@RequestBody VerifyOtpDTO dto) {

		moduleCoordinatorService.verifyOtp(dto.getEmail(), dto.getOtp());

		return ResponseEntity.status(HttpStatus.OK).body("Verified");
	}

	@PostMapping("/addpassword")
	public ResponseEntity<?> verify(@RequestBody AddPasswordDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(moduleCoordinatorService.addPassWord(dto.getEmail(), dto.getPassword()));
	}
	
	
}
