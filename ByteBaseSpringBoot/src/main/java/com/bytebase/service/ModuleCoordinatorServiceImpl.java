package com.bytebase.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.ModuleCoordinatorDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ModuleCoordinatorDTO;
import com.bytebase.dto.ModuleCoordinatorRespDTO;
import com.bytebase.dto.StudentRespDTO;
import com.bytebase.entities.EmailDetails;
import com.bytebase.entities.ModuleCoordinator;
import com.bytebase.entities.Student;

@Service
@Transactional
public class ModuleCoordinatorServiceImpl implements ModuleCoordinatorService{
	
	@Autowired
	private ModuleCoordinatorDao moduleCoordinatorDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	EmailService emailSend;
	
	@Override
	public ApiResponse addModuleCoordinator(ModuleCoordinatorDTO coordinator) {
		
		ModuleCoordinator mcoordinator = modelMapper.map(coordinator, ModuleCoordinator.class);
		ModuleCoordinator id = moduleCoordinatorDao.save(mcoordinator);
		
		return new ApiResponse("module coordinator created with Id="+id.getId());
		
	}
	
	@Override
	public ApiResponse deleteModuleCoordinator(Long id) {
		if(moduleCoordinatorDao.existsById(id)) {
			moduleCoordinatorDao.deleteById(id);
			return new ApiResponse("Student Delete successfully");
		}
		
		return new ApiResponse("invalid Id provided");
	}
	
	@Override
	public List<ModuleCoordinatorRespDTO> getAllModuleCoordinators() {
		return moduleCoordinatorDao.findAll().stream()
				.map(ModuleCoordinator -> modelMapper.map(ModuleCoordinator, ModuleCoordinatorRespDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse updateModuleCoordinator(Long id, ModuleCoordinator coordinator) {
		if(moduleCoordinatorDao.existsById(id)) {
			
			moduleCoordinatorDao.save(coordinator);
			
			return new ApiResponse("Updated the details");
		}
		
		return new ApiResponse("Error in Updating details");
	}

	@Override
	public String getModuleCoordinator(String email) {
		ModuleCoordinator student = moduleCoordinatorDao.findByEmail(email).orElseThrow();

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
	public void verifyOtp(String email, String otp) {
		ModuleCoordinator student = moduleCoordinatorDao.findByEmailAndOtp(email, otp).orElseThrow();
		System.out.println("In Verify " + student.getName());
		student.setOtp(null);
		moduleCoordinatorDao.save(student);
		
	}

	@Override
	public ModuleCoordinatorRespDTO addPassWord(String email, String password) {
		ModuleCoordinator student = moduleCoordinatorDao.findByEmail(email).orElseThrow();
		if(student.getOtp() == null) {
			student.setPassword(password);
			student.setOtp(null);
			
			ModuleCoordinatorRespDTO studentDto = modelMapper.map(student, ModuleCoordinatorRespDTO.class);
			
			return studentDto;
		}
		else {
			throw new RuntimeException("OTP verification is required before setting the password");
		}
	}
	
}
