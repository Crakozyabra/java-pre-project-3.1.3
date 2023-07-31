package ru.kata.spring.boot_security.demo.util;

import lombok.experimental.UtilityClass;
import ru.kata.spring.boot_security.demo.dto.AbstractBaseDto;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(AbstractBaseDto bean) {
        if (bean.getId() != null) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseDto bean, long id) {
        if (bean.getId() != null) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }
}