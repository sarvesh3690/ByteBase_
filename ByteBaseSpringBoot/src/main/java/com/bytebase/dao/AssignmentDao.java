package com.bytebase.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.Assignment;

public interface AssignmentDao extends JpaRepository<Assignment, Long> {

}
