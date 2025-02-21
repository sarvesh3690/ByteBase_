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
import com.bytebase.dto.StudentDTO;
import com.bytebase.dto.StudentUpdateDTO;
import com.bytebase.dto.VerifyOtpDTO;
import com.bytebase.entities.Student;
import com.bytebase.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getAllStudents() {
		if (studentService.getAllStudents().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@PostMapping("/addstudent")
	public ResponseEntity<?> addNewStudent(@RequestBody StudentDTO student) {

		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
	}

	@DeleteMapping("/deletestudent/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.deleteStudent(id));
	}

	@PutMapping("updatestudent/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO student) {
		
		
		return ResponseEntity.ok(studentService.updateStudent(id, student));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> Authentication(@RequestBody AuthenticateEmailDTO email) {
		String message = studentService.getStudent(email.getEmail());
		if (message.equals("add password"))
			return ResponseEntity.status(HttpStatus.OK).body("Student Found");
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).body("Student Found");
			
	}

	@PostMapping("/verifyotp")
	public ResponseEntity<?> verify(@RequestBody VerifyOtpDTO dto) {

		studentService.verifyOtp(dto.getEmail(), dto.getOtp());

		return ResponseEntity.status(HttpStatus.OK).body("Verified");
	}

	@PostMapping("/addpassword")
	public ResponseEntity<?> verify(@RequestBody AddPasswordDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.addPassWord(dto.getEmail(), dto.getPassword()));
	}

}
