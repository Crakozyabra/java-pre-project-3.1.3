package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.ValidationUtil;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    public static final String REST_URL = "/admin/rest/users";

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        log.info("create {}", userDto);
        ValidationUtil.checkNew(userDto);
        UserDto created = userService.save(userDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).body(created);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody UserDto userDto, @PathVariable Long id) {
        log.info("update");
        ValidationUtil.assureIdConsistent(userDto, id);
        userService.save(userDto);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        log.info("get");
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    @GetMapping
    public List<UserDto> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("delete");
        userService.delete(id);
    }
}
