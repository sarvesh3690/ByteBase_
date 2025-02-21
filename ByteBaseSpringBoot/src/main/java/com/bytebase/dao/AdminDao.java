package com.bytebase.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.Admin;
import com.bytebase.entities.Student;
import java.util.List;


public interface AdminDao extends JpaRepository<Admin, Long> {
		Optional<Admin> findByEmail(String email);
		Optional<Admin> findByPassword(String password);
}
