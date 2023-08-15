package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "Please enter a valid postal code")
    @Size(min = 3)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 5)
    @Digits(integer = 5, fraction = 0, message = "Please enter a valid postal code")
    @Size(min = 5, max = 5, message = "Please enter a valid postal code")
    private String postalCode;

    @Column(name = "country", nullable = false, length = 80)
    @NotBlank
    @Size(min = 3)
    private String country;
}