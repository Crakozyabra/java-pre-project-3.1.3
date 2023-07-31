package ru.kata.spring.boot_security.demo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserDto toTo(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toToList(Collection<User> users);
}
