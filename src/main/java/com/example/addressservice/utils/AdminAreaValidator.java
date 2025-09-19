package com.example.addressservice.utils;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.example.addressservice.domain.AdminLevel;
import com.example.addressservice.domain.enumeration.AdminArea;

@Component
public class AdminAreaValidator {
	
	private static final String CODE_PATTERN = "^[0-9]{2}(?:[0-9]{2}){0,3}$";
	public void validate(AdminArea request) {
		if(Objects.isNull(request)) {
			throw new IllegalArgumentException("request is required");
		}
		if(Objects.isNull(request.getLevel())) {
			throw new IllegalArgumentException("level is required");
		}
		if(Objects.isNull(request.getCode()) || request.getCode().isBlank()) {
			throw new IllegalArgumentException("code is required"); 
		}
		if(!CODE_PATTERN.matches(request.getCode())) {
			throw new IllegalArgumentException("code must look like 12 or 12-03-09-02"); 	
		}
		final int dept = request.getCode().split("-").length;
		final int expectedDept = request.getLevel().depth(); 
		if(dept != expectedDept) {
			throw new IllegalArgumentException("Code dept does not match level: " + request.getLevel() );
		}
		if(request.getLevel() == AdminLevel.PROVINCE) {
			if(request.getParentCode() != null) {
				throw new IllegalArgumentException("ParentCode must be null for Provice"); 
			}
		}else {
			if(Objects.isNull(request.getParentCode()) || request.getParentCode().isBlank()) {
				throw new IllegalArgumentException("ParentCode is required for:" + request.getLevel() ); 
			}
			if(!request.getCode().startsWith(request.getParentCode())) {
				throw new IllegalArgumentException("code must start with parentCode"); 
			}
		}
	}
} 
