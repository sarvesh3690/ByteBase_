package com.bytebase.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Long> {

}
