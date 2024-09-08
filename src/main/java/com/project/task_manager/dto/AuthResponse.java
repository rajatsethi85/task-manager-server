package com.project.task_manager.dto;

import com.project.task_manager.enums.UserRole;
import lombok.Data;

/**
 * DTO class for login response after successful authentication.
 */
@Data
public class AuthResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
    private String name;
}
