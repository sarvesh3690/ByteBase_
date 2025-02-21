package com.bytebase.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.StudentDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.StudentDTO;
import com.bytebase.dto.StudentRespDTO;
import com.bytebase.dto.StudentUpdateDTO;
import com.bytebase.entities.EmailDetails;
import com.bytebase.entities.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	EmailService emailSend;

	@Override
		public String getStudent(String email) {
			Student student = studentDao.findByEmail(email).orElseThrow();

			if (student.getPassword() == null) {
				String otp = String.format("%04d", new Random().nextInt(10000));
				System.out.println("In Otp" + otp);
				student.setOtp(otp);
				
				emailSend.sendMail(new EmailDetails(student.getEmail(), "OTP: " + otp, "OTP Verification"));
				return "add password";
			}
			
			return "password exists";

		}

		@Override
		public void verifyOtp(String email, String Otp) {
			Student student = studentDao.findByEmailAndOtp(email, Otp).orElseThrow();
			System.out.println("In Verify " + student.getName());
			student.setOtp(null);
			studentDao.save(student);
		}

		@Override
		public StudentRespDTO addPassWord(String email, String password) {
			Student student = studentDao.findByEmail(email).orElseThrow();
			if(student.getOtp() == null) {
				student.setPassword(password);
				student.setOtp(null);
				
				StudentRespDTO studentDto = modelMapper.map(student, StudentRespDTO.class);
				
				return studentDto;
			}
			else {
				throw new RuntimeException("OTP verification is required before setting the password");
			}
		}

	@Override
	public ApiResponse addStudent(StudentDTO student) {

		Student students = modelMapper.map(student, Student.class);

		Student id = studentDao.save(students);

		return new ApiResponse("Student created with Id=" + id.getId());

	}

	@Override
	public ApiResponse deleteStudent(Long id) {
		if (studentDao.existsById(id)) {
			studentDao.deleteById(id);
			return new ApiResponse("Student Delete successfully");
		}

		return new ApiResponse("invalid Id provided");
	}

	@Override
	public List<StudentRespDTO> getAllStudents() {
		return studentDao.findAll().stream().map(student -> modelMapper.map(student, StudentRespDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse updateStudent(Long id, StudentUpdateDTO student) {
		if (studentDao.existsById(id)) {
			
			Student stud = studentDao.findById(id).orElseThrow();
			stud.setName(student.getName());
			stud.setPhone(student.getPhone());
			studentDao.save(stud);

			return new ApiResponse("Updated the details");
		}

		return new ApiResponse("Error in Updating details");
	}

}
