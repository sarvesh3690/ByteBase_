package com.bytebase.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recording")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recording extends BaseEntity {
    private String topic;
    
    @OneToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty facultyName;
    
    private String link;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;
}
