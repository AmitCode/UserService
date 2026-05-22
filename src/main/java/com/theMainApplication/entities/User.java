package com.theMainApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MAIN_APP_USERS_TABLE")
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String userName;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userContactNumber;
    private String userEmailId;
    private Boolean isUserActive;
    private Boolean isMobileVerified;
    private Boolean isEmailVerified;
    private String password;
    private String confirmPassword;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo",fetch = FetchType.EAGER)
    private List<UserAddress> addresses;

    public User() {
        this(true, false, false);
    }
    public User(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

    public User(Boolean isUserActive, Boolean isEmailVerified, Boolean isMobileVerified){
        this.isUserActive = isUserActive;
        this.isMobileVerified = isMobileVerified;
        this.isEmailVerified = isEmailVerified;
    }
}
