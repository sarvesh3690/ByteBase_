package com.bytebase.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classwork")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassWork extends BaseEntity{
  
	private String topic;
    private String zipFile;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty teacher;
}
