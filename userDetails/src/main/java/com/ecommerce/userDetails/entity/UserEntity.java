package com.ecommerce.userDetails.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userLoginDetails")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "userName", unique = true,nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    public UserEntity(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public UserEntity(Long userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName != null ? userName : "Unknown";
        this.password = password != null ? password : "";
        this.email = email != null ? email : "";
    }


}
