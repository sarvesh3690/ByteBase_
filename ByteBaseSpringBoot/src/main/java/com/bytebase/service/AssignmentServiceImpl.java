package com.bytebase.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dao.AssignmentDao;
import com.bytebase.dao.ModuleDao;
import com.bytebase.dao.StudentDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.AssignmentDTO;
import com.bytebase.dto.AssignmentNoRespDTO;
import com.bytebase.dto.AssignmentRespDTO;
import com.bytebase.dto.AssignmentUploadDTO;
import com.bytebase.entities.Assignment;
import com.bytebase.entities.Student;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService{
	
	@Autowired
	private AssignmentDao assignmentDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ModuleDao moduledao;

	@Autowired
	private StudentDao student;
	
	@Override
	public ApiResponse addAssignemt(MultipartFile file, AssignmentDTO assignment) throws IOException {
		
		com.bytebase.entities.Module module = moduledao.findById(assignment.getModuleId()).orElseThrow();
	

		
		
		String uploadDir = module.getName()+"/";
		
		String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		
		Path filePath = Paths.get(uploadDir+filename);
		
		Files.createDirectories(filePath.getParent());
		
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		
		Assignment classs = modelMapper.map(assignment, Assignment.class);
		
		classs.setPdfFile(filePath.toString());
		
		
		
		classs.setModule(module);

		Assignment work = assignmentDao.save(classs);
		
		return new ApiResponse("module coordinator created with Id="+work.getId());
		
	}
	
	
	
	@Override
	public List<AssignmentRespDTO> getAssignments() {
		return assignmentDao.findAll().stream()
				.map(classWork -> {
					
					AssignmentRespDTO dto = modelMapper.map(classWork, AssignmentRespDTO.class);
		
				
					com.bytebase.entities.Module module = classWork.getModule();
					
					
					dto.setModuleName(module.getName());
					
					return dto;
					
				})
					
				.collect(Collectors.toList());
	}



	@Override
	public ApiResponse uploadAssignment(MultipartFile file, AssignmentUploadDTO studentId) throws IOException {
		Student stud = student.findById(studentId.getStudentId()).orElseThrow();
		Assignment assign = assignmentDao.findById(studentId.getAssignmentId()).orElseThrow();
		
		stud.getAssignments().add(assign);
		assign.getStudents().add(stud);
		
		String uploadDir = stud.getName()+"/";
		
		String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		
		Path filePath = Paths.get(uploadDir+filename);
		
		Files.createDirectories(filePath.getParent());
		
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		
		student.save(stud);
		assignmentDao.save(assign);
		
		return new ApiResponse("Uploaded");
	}



	@Override
	public AssignmentNoRespDTO getUploadAssignment(Long id) {
	
		Long assign = assignmentDao.count();
		Student stud = student.findById(id).orElseThrow();
		int uploadCount = stud.getAssignments().size();
//		for(Student i: assign.getStudents()) {
//			System.out.println("Student Found");
//			if(i.getId() == id) {
//				System.out.println("Student Found");
//				uploadCount++;
//			}
//		}
		
		AssignmentNoRespDTO dto = new AssignmentNoRespDTO();
		dto.setUploaded(String.valueOf(uploadCount));
		dto.setTotal(String.valueOf(assign));
		
		return dto;
		
	}
	
	
}
