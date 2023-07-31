package ru.kata.spring.boot_security.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto extends AbstractBaseDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<RoleDto> roles;
}
