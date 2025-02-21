package com.bytebase.service;

import java.util.List;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.StudentDTO;
import com.bytebase.dto.StudentRespDTO;
import com.bytebase.dto.StudentUpdateDTO;
import com.bytebase.entities.Student;

public interface StudentService {
	
	ApiResponse addStudent(StudentDTO student);
	
	ApiResponse deleteStudent(Long id);

	List<StudentRespDTO> getAllStudents();
	
	ApiResponse updateStudent(Long id, StudentUpdateDTO student);
	
	String getStudent(String email);
	
	void verifyOtp(String email, String Otp);

	StudentRespDTO addPassWord(String email, String password);

}
