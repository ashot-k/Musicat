package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "local_user")
public class LocalUser implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String passwrd) {
        this.password = passwrd;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
