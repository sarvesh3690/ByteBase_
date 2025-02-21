package com.bytebase.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.ModuleCoordinator;
import com.bytebase.entities.Student;

public interface ModuleCoordinatorDao extends JpaRepository<ModuleCoordinator, Long> {
	Optional<ModuleCoordinator> findByEmail(String email);
	Optional<ModuleCoordinator> findByEmailAndOtp(String email, String otp);
}
