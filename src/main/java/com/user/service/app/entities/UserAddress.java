package com.user.service.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "MAIN_APP_ADDRESS_TABLE")
@AllArgsConstructor
@Setter
@Getter
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addressType;
    private String home;
    private String pinCode;
    private String area;
    private String city;
    private String state;
    private String country;
    private String isPrimaryAddress;
    private String isAddressActive;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userInfo;

    public UserAddress() {
        this("Y");
    }

    public UserAddress(String isAddressActive) {
        this.isAddressActive = isAddressActive;
    }
}
