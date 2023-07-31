package ru.kata.spring.boot_security.demo.dto;

import org.mapstruct.Mapper;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toTo(Role role);

    Role toEntity(RoleDto roleDto);

   /* @Mapping(target = "id", expression = "java(Long.parseLong(role))")
    RoleDto stringToDto(String role);*/


    Set<RoleDto> toToSet(Collection<Role> roles);

   /* Set<RoleDto> toLongToSet(Collection<Long> roles);*/
}