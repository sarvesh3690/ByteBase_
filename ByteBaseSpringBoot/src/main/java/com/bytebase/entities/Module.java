package com.bytebase.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Module extends BaseEntity{
   

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty teacher;

    @OneToMany(mappedBy = "module")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "module")
    private List<ClassWork> classworks;
}
