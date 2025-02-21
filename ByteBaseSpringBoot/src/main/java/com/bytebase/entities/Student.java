package com.bytebase.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student extends BaseEntity{
    
	@Column(nullable = false, unique = true)
    private String prn;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    
    private String password;
    
    private String otp;

    @ManyToMany
    @JoinTable(
        name = "student_assignments",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "assignment_id")
    )
    private List<Assignment> assignments = new ArrayList<>();
}
