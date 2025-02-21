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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dao.ClassWorkDao;
import com.bytebase.dao.FacultyDao;
import com.bytebase.dao.ModuleDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ClassWorkDTO;
import com.bytebase.dto.ClassWorkRespDTO;
import com.bytebase.entities.ClassWork;
import com.bytebase.entities.Faculty;

@Service
@Transactional
public class ClassWorkServiceImpl implements ClassWorkService{
	
	@Autowired
	private ClassWorkDao classWorkDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ModuleDao moduledao;

	@Autowired
	private FacultyDao facultyDao;
	
	@Override
	public ApiResponse addClassWork(MultipartFile file, ClassWorkDTO classWork) throws IOException {
		
		com.bytebase.entities.Module module = moduledao.findById(classWork.getModuleId()).orElseThrow();
		Faculty faculty = facultyDao.findById(classWork.getTeacherId()).orElseThrow();

		
		
		String uploadDir = module.getName()+"/";
		
		String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		
		Path filePath = Paths.get(uploadDir+filename);
		
		Files.createDirectories(filePath.getParent());
		
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		
		ClassWork classs = modelMapper.map(classWork, ClassWork.class);
		
		classs.setZipFile(filePath.toString());
		
		classs.setTeacher(faculty);
		
		classs.setModule(module);

		ClassWork work = classWorkDao.save(classs);
		
		return new ApiResponse("module coordinator created with Id="+work.getId());
		
	}
	
	@Override
	public ApiResponse deleteClassWork(Long id) {
		if(classWorkDao.existsById(id)) {
			classWorkDao.deleteById(id);
			return new ApiResponse("Student Delete successfully");
		}
		
		return new ApiResponse("invalid Id provided");
	}
	
	@Override
	public List<ClassWorkRespDTO> getAllClassWork() {
		return classWorkDao.findAll().stream()
				.map(classWork -> {
					
					ClassWorkRespDTO dto = modelMapper.map(classWork, ClassWorkRespDTO.class);
		
					Faculty faculty = classWork.getTeacher();
					com.bytebase.entities.Module module = classWork.getModule();
					
					dto.setTeacher(faculty.getName());
					dto.setModule(module.getName());
					
					return dto;
					
				})
					
				.collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse updateClassWork(Long id, ClassWork classWork) {
		if(classWorkDao.existsById(id)) {
			
			classWorkDao.save(classWork);
			
			return new ApiResponse("Updated the details");
		}
		
		return new ApiResponse("Error in Updating details");
	}
	
}
