package com.user.service.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    @NotBlank(message = "Auth Service userId can't be blank")
    private Long authUserId;

    @NotBlank(message = "User Name can't be blank")
    private String userName;

    @NotBlank(message = "User Email is required")
    @Email(message = "Email must be in proper format")

    private String userEmail;
    @NotBlank(message = "User Password can't be empty!...")
    private String password;
}
