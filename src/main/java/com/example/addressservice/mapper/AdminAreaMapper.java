package com.example.addressservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.addressservice.domain.enumeration.AdminArea;
import com.example.addressservice.dto.AdminAreaCreateRequestDTO;

@Mapper(componentModel = "spring")
public interface AdminAreaMapper {
	
	@Mapping(target = "createdAt", ignore = true )
	@Mapping(target = "updatedAt", ignore = true )
	@Mapping(target = "version", ignore = true )
	@Mapping(target = "path", expression = "java(buildPath(dto.getCode()))" )
	
	AdminArea toEntity(AdminAreaCreateRequestDTO dto);
	
	default List<String> buildPath(String code){ 
		if(code == null || code.isBlank()) {
			return List.of();
		}
		String[] parts =code.split("-");
		List<String> path = new ArrayList<>(parts.length);
		String acc = "";
		for(int i = 0; i < parts.length; i++) {
			if(i == 0) {
				acc = parts[i];
			}else {
				acc = acc + "-" + parts[i];
			}
			path.add(acc);
		}
		return path;
	}
	
	@Mapping(target = "createdAt", ignore = true )
	@Mapping(target = "updatedAt", ignore = true )
	@Mapping(target = "version", ignore = true )
	@Mapping(target = "level", ignore = true )
	@Mapping(target = "code", ignore = true )
	@Mapping(target = "path", ignore = true )
	@Mapping(target = "parentCode", ignore = true )
	void update(@MappingTarget AdminArea target, AdminAreaCreateRequestDTO dto);

}
