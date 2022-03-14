package com.bk.dao.model;

import javax.persistence.*;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 07/03/22
 */
@Entity
@Table(name = "USERS")
public class TestUserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email")
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
}
