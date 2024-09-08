package com.project.task_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO class for sign up request body.
 */
@Data
public class SignupRequest {
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
}
