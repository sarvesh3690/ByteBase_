package com.bytebase.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
		Optional<Student> findByEmail(String email);
		Optional<Student> findByEmailAndOtp(String email, String otp);
}
