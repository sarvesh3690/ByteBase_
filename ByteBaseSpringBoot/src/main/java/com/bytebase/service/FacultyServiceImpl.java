package com.bytebase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.FacultyDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.FacultyDTO;
import com.bytebase.dto.FacultyRespDTO;
import com.bytebase.entities.Faculty;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService{
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addFaculty(FacultyDTO faculty) {
		
		Faculty mcoordinator = modelMapper.map(faculty, Faculty.class);
		Faculty id = facultyDao.save(mcoordinator);
		
		return new ApiResponse("module coordinator created with Id="+id.getId());
		
	}

	@Override
	public List<FacultyRespDTO> getAllFaculty() {
		return facultyDao.findAll().stream()
				.map(faculty -> modelMapper.map(faculty, FacultyRespDTO.class))
				.collect(Collectors.toList());
	}
	
	
	
}
