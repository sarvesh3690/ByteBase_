package com.bytebase.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytebase.entities.Recording;

public interface RecordingDao extends JpaRepository<Recording, Long> {

}
