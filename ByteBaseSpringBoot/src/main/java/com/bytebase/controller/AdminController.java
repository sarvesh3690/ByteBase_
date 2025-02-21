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
import com.bytebase.dto.AdminEmailDTO;
import com.bytebase.dto.AdminPasswordDTO;
import com.bytebase.dto.AuthenticateEmailDTO;
import com.bytebase.dto.StudentDTO;
import com.bytebase.dto.VerifyOtpDTO;
import com.bytebase.entities.Student;
import com.bytebase.service.AdminService;
import com.bytebase.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> verify(@RequestBody AdminEmailDTO dto) {

		adminService.verifyAdmin(dto.getEmail());

		return ResponseEntity.status(HttpStatusCode.valueOf(204)).body("Verified");
	}
	
	@PostMapping("/addpassword")
	public ResponseEntity<?> verifyPass(@RequestBody AdminPasswordDTO dto) {

		adminService.verifyPassword(dto.getPassword());

		return ResponseEntity.status(HttpStatus.OK).body("Verified");
	}

	

}
