package com.soCompany.sunflower.entity.fields;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    BLOCKED;

    @Override
    public String getAuthority() {
        return name();
    }
}
