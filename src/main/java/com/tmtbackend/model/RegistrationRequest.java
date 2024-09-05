package com.tmtbackend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Full name can't be empty.")
    @NotBlank(message = "Full name can't be blank.")
    private String fullname;
    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email can't be empty.")
    @NotBlank(message = "Email can't be blank.")
    private String email;
    @Size(min = 8,message = "Password should be 8 characters long minimum.")
    @NotEmpty(message = "Password can't be empty.")
    @NotBlank(message = "Password can't be blank.")
    private String password;
    private long mobileNo;
    @NotEmpty(message = "Nationality can't be empty.")
    @NotBlank(message = "Nationality can't be blank.")
    private String nationality;

}
