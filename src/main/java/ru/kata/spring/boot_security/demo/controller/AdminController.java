package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.propertyeditor.RolePropertyEditor;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private UserService userService;

    // https://www.baeldung.com/spring-mvc-custom-property-editor
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new RolePropertyEditor());
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        log.info("delete");
        userService.delete(id);
        return "redirect:/user";
    }

    @PostMapping("/users")
    public String processForm(User user) {
        log.info("processForm");
        userService.save(user);
        return "redirect:/user";
    }
}
