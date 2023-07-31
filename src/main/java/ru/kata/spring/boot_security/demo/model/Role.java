package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Slf4j
@Table(name = "roles")
public class Role extends AbstractBaseEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        log.info("getAuthority");
        System.out.println("getAuthority");
        return "ROLE_" + name;
    }
}