package com.bytebase.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebase.dao.AdminDao;
import com.bytebase.dto.AdminRespDTO;
import com.bytebase.entities.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void verifyAdmin(String email) {
		Admin admin = adminDao.findByEmail(email).orElseThrow();
		
	}

	@Override
	public AdminRespDTO verifyPassword(String password) {
		Admin admin = adminDao.findByPassword(password).get();
		
		AdminRespDTO dto = modelMapper.map(admin, AdminRespDTO.class);
		
		return dto;
	}
	
	
	

}
