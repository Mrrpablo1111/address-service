package com.example.addressservice.dto;

import java.time.Instant;
import java.util.List;

import com.example.addressservice.domain.AdminLevel;

import lombok.Data;

@Data
public class AdminAreaResponseDTO {
private String code;
	
	private AdminLevel level;
	
	private String parentCode;  
	private String nameKh;
	private String nameEn;
	private List<String>path;
	private Instant createdAt;
	private Instant updatedAt; 
}
