package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    public static final String PASSWORD_ERROR_MESSAGE = """
                        Please enter a password that has: <br>
                        At least 5 characters. <br>
                        At least one letter. <br>
                        At least one number.""";





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "TEXT")
    @NotBlank(message = "Enter valid username")
    private String username;

    @Column(name = "passwrd", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"
            , message = """
            Please enter a password that has <br>
            At least 5 characters <br>
            At least one letter <br>
            At least one number""")
    private String passwrd;

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

}