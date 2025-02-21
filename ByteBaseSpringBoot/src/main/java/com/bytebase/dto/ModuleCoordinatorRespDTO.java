package com.bytebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleCoordinatorRespDTO extends BaseDTO {
	private String name;
    private String email;
    private String phone;
    private String role="modulecoordinator";

   
}
