package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.propertyeditor.RolePropertyEditor;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class UserController {

    private UserService userService;

    private RoleService roleService;

    // https://www.baeldung.com/spring-mvc-custom-property-editor
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new RolePropertyEditor());
    }

    @GetMapping
    public String getAll(ModelMap model) {
        log.info("getAll");
        model.addAttribute("users", userService.getAll());
        return "main";
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        log.info("delete");
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/users")
    public String getForm(@RequestParam(required = false) Long modifiedUserId, ModelMap model) {
        log.info("getForm");
        User user = Objects.isNull(modifiedUserId) ? new User() : userService.findById(modifiedUserId);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAll());
        return "form";
    }

    @PostMapping("/users")
    public String processForm(User user) {
        log.info("processForm");
        userService.save(user);
        return "redirect:/admin";
    }
}