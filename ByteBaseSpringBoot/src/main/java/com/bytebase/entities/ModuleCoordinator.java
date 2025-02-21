package com.bytebase.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "modulecoordinator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModuleCoordinator extends BaseEntity {
    

    private String name;
    private String email;
    private String password;
    private String phone;
    private String otp;

    
}
