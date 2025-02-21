package com.bytebase.service;

import java.util.List;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.RecordingDTO;
import com.bytebase.dto.RecordingRespDTO;
import com.bytebase.entities.Recording;

public interface RecordingService {
	
	List<RecordingRespDTO> getAllRecordings();
	
	ApiResponse addRecording(RecordingDTO record);
	
	ApiResponse deleteRecording(Long id);
	
	ApiResponse updateRecording(Long id, Recording record);
}
