package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/")
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
    public String getAll(ModelMap model, @AuthenticationPrincipal User self) {

        log.info("getAll");
        model.addAttribute("self", userService.findById(self.getId()));
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("allRoles", roleService.getAll());
        return "admin-panel";
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        log.info("delete");
        userService.delete(id);
        return "redirect:/";
    }

    /*
        @GetMapping("/users")
        public String getForm(@RequestParam(required = false) Long modifiedUserId, ModelMap model) {
            log.info("getForm");
            User user = Objects.isNull(modifiedUserId) ? new User() : userService.findById(modifiedUserId);
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roleService.getAll());
            return "form";
        }
     */
    @PostMapping("/users")
    public String processForm(User user, @AuthenticationPrincipal User self) {
        log.info("processForm {}", user);
        userService.save(user);
        return "redirect:/";
    }
}