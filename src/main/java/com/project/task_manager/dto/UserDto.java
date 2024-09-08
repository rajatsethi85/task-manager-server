package com.project.task_manager.dto;

import com.project.task_manager.enums.UserRole;
import lombok.Data;

/**
 * DTO class for User signup response body.
 */
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
