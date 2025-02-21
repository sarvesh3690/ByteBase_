package com.bytebase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.FacultyDao;
import com.bytebase.dao.ModuleDao;
import com.bytebase.dao.RecordingDao;
import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.RecordingDTO;
import com.bytebase.dto.RecordingRespDTO;
import com.bytebase.entities.Faculty;
import com.bytebase.entities.Recording;

@Service
@Transactional
public class RecordingServiceImpl implements RecordingService {

	@Autowired
	private RecordingDao recordingDao;

	@Autowired
	private ModuleDao moduledao;

	@Autowired
	private FacultyDao facultyDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse deleteRecording(Long id) {
		if(recordingDao.existsById(id)) {
			recordingDao.deleteById(id);
			return new ApiResponse("Recording deleted successfully");
		}
		return null;
	}

	public List<RecordingRespDTO> getAllRecordings() {
		return recordingDao.findAll().stream().map(recording -> {
			RecordingRespDTO dto =  modelMapper.map(recording, RecordingRespDTO.class);
			Faculty faculty = recording.getFacultyName();
			com.bytebase.entities.Module module = recording.getModule();
			
			dto.setFaculty(faculty.getName());
			dto.setModuleName(module.getName());
			
			return dto;
		}).collect(Collectors.toList());
	}

	public ApiResponse addRecording(RecordingDTO recording) {

		com.bytebase.entities.Module module = moduledao.findById(recording.getModuleId()).orElseThrow();
		Faculty faculty = facultyDao.findById(recording.getFacultyId()).orElseThrow();
		
		/*
		 * System.out.println("Inn Imple"+faculty.toString());
		 * System.out.println("Inn Imple"+module.toString());
		 */
		Recording addrecording = modelMapper.map(recording, Recording.class);
		addrecording.setFacultyName(faculty);
		addrecording.setModule(module);

		Recording recordings = recordingDao.save(addrecording);

		return new ApiResponse("module coordinator created with Id=" + recordings.getId());
	}

	public ApiResponse updateRecording(Long id, Recording record) {
		if (recordingDao.existsById(id)) {

			recordingDao.save(record);

			return new ApiResponse("Updated the details");
		}

		return new ApiResponse("Error in Updating details");
	}

}
