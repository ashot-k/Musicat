package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    public static final String[] ROLES = {"ROLE_CUSTOMER","ROLE_ADMIN"};
    public Role() {
    }

    public Role(LocalUser user) {
        this.username = user;
        this.type = ROLES[0];
    }
    public Role(LocalUser user, String role) {
        this.username = user;
        this.type = role;
    }
    @Id
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private LocalUser username;

    @Column(name = "role")
    private String type;

    public LocalUser getUsername() {
        return username;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
