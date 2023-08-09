package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "local_user")
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "TEXT")
    @NotBlank(message = "Enter valid username")
    private String username;

    @Column(name = "passwrd", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    private String passwrd;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Enter valid first name")
    private String firstName;

    @Column(name = "email", nullable = false)
    @Email(message = "Enter valid email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_addresses_id")
    @NotNull
    private Address addresses;

}