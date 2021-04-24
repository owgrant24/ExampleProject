package com.github.owgrant24.one.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 50, message = "Surname should be from 1 to 50 symbols")
    @Column(name = "surname")
    private String surname;

    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 6, message = "Minimum 6 symbols")
    private String password;

    @Transient                                                              // при сериализации , поле не запишется
    private String confirmPassword;

    @ManyToMany(
            fetch = FetchType.EAGER
            , cascade = {CascadeType.MERGE
            , CascadeType.PERSIST
            , CascadeType.DETACH
            , CascadeType.REFRESH}
    )
    @JoinTable(
            name = "user_roles"                                              // Название доп таблицы
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public String toString() {
        return name + " " + surname + " " + email;
    }
}