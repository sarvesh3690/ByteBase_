package com.bytebase.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Assignment extends BaseEntity {
  

    private String title;
    private Date deadline;
    private String pdfFile;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module; // Linked to the module

    @ManyToMany(mappedBy = "assignments")
    private List<Student> students = new ArrayList<>();
}
