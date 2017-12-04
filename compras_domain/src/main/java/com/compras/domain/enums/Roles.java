package com.compras.domain.enums;

/**
 * Enumerated roles.
 */
public enum Roles {
    ADMIN, PREMIUM_MEMBER, MEMBER;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
}
