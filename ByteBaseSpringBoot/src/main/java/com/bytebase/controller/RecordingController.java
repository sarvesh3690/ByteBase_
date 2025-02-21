package com.bytebase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bytebase.dto.RecordingDTO;
import com.bytebase.entities.Recording;
import com.bytebase.service.RecordingService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recording")
public class RecordingController {
	
	@Autowired
	private RecordingService recordingService;
	
	@GetMapping
	public ResponseEntity<?> getAllRecording(){
		if(recordingService.getAllRecordings().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return ResponseEntity.ok(recordingService.getAllRecordings());
	}
	
	@PostMapping("/addrecording")
	public ResponseEntity<?> addNewRecording(@RequestBody RecordingDTO record){
		System.out.print("In addrecording"+record.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(recordingService.addRecording(record));
	}
	
	@DeleteMapping("/deleterecording/{id}")
	public ResponseEntity<?> deleteRecording(@PathVariable Long id){
		return ResponseEntity.ok(recordingService.deleteRecording(id));
	}
	
	@PutMapping("updaterecording/{id}")
	public ResponseEntity<?> updateRecording(@PathVariable Long id, @RequestBody Recording record) {
		return ResponseEntity.ok(recordingService.updateRecording(id, record));
	}
	
	
}
