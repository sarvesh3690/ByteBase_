package com.bytebase.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bytebase.dto.ApiResponse;
import com.bytebase.dto.ClassWorkDTO;
import com.bytebase.entities.ClassWork;
import com.bytebase.entities.Module;
import com.bytebase.service.ClassWorkService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/classwork")
public class ClassWorkController {

	@Autowired
	private ClassWorkService classWorkService;

	@GetMapping
	public ResponseEntity<?> getAllClassWork() {
		if (classWorkService.getAllClassWork().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.ok(classWorkService.getAllClassWork());
	}

	@PostMapping("/addclasswork")
	public ResponseEntity<?> addClassWorks(@RequestPart MultipartFile inputfile, @RequestParam String classWork) {

		try {

		
			ObjectMapper mapper = new ObjectMapper(); 
			ClassWorkDTO classWork1 =
			 mapper.readValue(classWork, ClassWorkDTO.class);
			
			System.out.println("Received file: " + inputfile.getOriginalFilename());
	        System.out.println("Received classWork: " + classWork);
			 
			return ResponseEntity.status(HttpStatus.CREATED).body(classWorkService.addClassWork(inputfile, classWork1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!!"));
	}

	@DeleteMapping("/deleteclasswork/{id}")
	public ResponseEntity<?> deleteClassWork(@PathVariable Long id) {
		return ResponseEntity.ok(classWorkService.deleteClassWork(id));
	}

	@PutMapping("updateclasswork/{id}")
	public ResponseEntity<?> updateClassWork(@PathVariable Long id, @RequestBody ClassWork classWork) {
		return ResponseEntity.ok(classWorkService.updateClassWork(id, classWork));
	}
	
	@GetMapping("/classwork/download/{filename}")
	public ResponseEntity<?> downloadFile(@PathVariable String filename) {
	    Module module = new Module();
		try {
	        Path filePath = Paths.get(module.getName()).resolve(filename); // Replace UPLOAD_DIRECTORY with actual path
	        Resource resource = new UrlResource(filePath.toUri());

	        if (resource.exists()) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                    .body(resource);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}

}
