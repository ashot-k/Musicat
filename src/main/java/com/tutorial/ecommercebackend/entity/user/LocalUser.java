package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "local_user")
public class LocalUser {

    public static final String PASSWORD_ERROR_MESSAGE = """
            Please enter a password that has: <br>
            At least 5 characters. <br>
            At least one letter. <br>
            At least one number.""";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Enter valid username")
    private String username;

    @Column(name = "passwrd", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"
            , message = PASSWORD_ERROR_MESSAGE)
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Enter valid first name")
    private String firstName;

    @Column(name = "email", nullable = false)
    @Email(message = "Enter a valid email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_addresses_id")
    @Valid
    private Address addresses;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "username")
    private Role role;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwrd) {
        this.password = passwrd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
