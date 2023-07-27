package ru.kata.spring.boot_security.demo.propertyeditor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.model.Role;

import java.beans.PropertyEditorSupport;

// https://www.baeldung.com/spring-mvc-custom-property-editor
@Slf4j
public class RolePropertyEditor extends PropertyEditorSupport {

    @SuppressWarnings("deprecation")
    @Override
    public void setAsText(String text) {
        log.info("RolePropertyEditor: {}", text);
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            Role role = new Role();
            role.setId(Long.parseLong(text));
            setValue(role);
        }
    }
}