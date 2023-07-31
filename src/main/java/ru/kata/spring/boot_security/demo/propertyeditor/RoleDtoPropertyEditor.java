package ru.kata.spring.boot_security.demo.propertyeditor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.dto.RoleDto;

import java.beans.PropertyEditorSupport;

// https://www.baeldung.com/spring-mvc-custom-property-editor
@Slf4j
public class RoleDtoPropertyEditor extends PropertyEditorSupport {

    @SuppressWarnings("deprecation")
    @Override
    public void setAsText(String text) {
        log.info("RolePropertyEditor: {}", text);
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(Long.parseLong(text));
            setValue(roleDto);
        }
    }
}