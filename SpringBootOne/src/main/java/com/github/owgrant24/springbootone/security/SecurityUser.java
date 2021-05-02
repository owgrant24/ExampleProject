package com.github.owgrant24.springbootone.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class SecurityUser extends User {
    private String name;
    private String surname;

    public SecurityUser(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        name = "";
        surname = "";
    }

    public SecurityUser(
            String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        name = "";
        surname = "";
    }

    public SecurityUser(
            String email, String password, Collection<? extends GrantedAuthority> authorities,
            String name, String surname) {
        super(email, password, authorities);
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
