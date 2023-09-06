package com.bk.authservice.entity;


import java.util.Objects;

/**
 * Represents a User in this application.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 03/03/22
 */
//@Entity
//@Table(name = "USERS")
public class User implements YaasEntity {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @Column(name = "username", nullable = false)
    private String username;

//    @Column(name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
