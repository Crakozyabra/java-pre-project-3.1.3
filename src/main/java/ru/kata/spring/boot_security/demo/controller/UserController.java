package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/")
@Slf4j
public class UserController {

    private UserService userService;

    private RoleService roleService;

    @GetMapping
    public String getAll(ModelMap model, @AuthenticationPrincipal User self) {
        log.info("getAll {}", self.getAuthorities());
        model.addAttribute("self", userService.findById(self.getId()));
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("allRoles", roleService.getAll());
        return "main";
    }
}