package com.bytebase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.FacultyDao;
import com.bytebase.dao.ModuleDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ModuleDTO;
import com.bytebase.dto.ModuleRespDTO;
import com.bytebase.entities.Faculty;
import com.bytebase.entities.Module;


@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addModule(ModuleDTO module) {
		Faculty faculty = facultyDao.findById(module.getFacultyId()).orElseThrow();
		
		com.bytebase.entities.Module mod = modelMapper.map(module, com.bytebase.entities.Module.class);
		
		mod.setTeacher(faculty);
	    
		Module m1 = moduleDao.save(mod); 
		
		return new ApiResponse("Module added"+m1.getId());
	}

	@Override
	public List<ModuleRespDTO> getAllModules() {
		return moduleDao.findAll().stream()
				.map(Mod -> {
					
				ModuleRespDTO dto = modelMapper.map(Mod, ModuleRespDTO.class);
				
				Faculty f = Mod.getTeacher();
				dto.setFacultyId(f.getId());
				dto.setFacultyName(f.getName());
				return dto;
				})
				.collect(Collectors.toList());

	}

}
