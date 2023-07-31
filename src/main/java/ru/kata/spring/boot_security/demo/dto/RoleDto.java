package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDto extends AbstractBaseDto {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
