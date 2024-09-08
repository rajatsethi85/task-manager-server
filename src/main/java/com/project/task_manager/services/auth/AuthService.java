package com.project.task_manager.services.auth;

import com.project.task_manager.dto.SignupRequest;
import com.project.task_manager.dto.UserDto;

/**
 * Interface for AuthService.
 */
public interface AuthService {
   UserDto signupUser(SignupRequest signupRequest);

   boolean hasUserWithEmail(String email);
}
