package com.project.task_manager.services.auth;

import com.project.task_manager.dto.SignupRequest;
import com.project.task_manager.dto.UserDto;
import com.project.task_manager.entities.User;
import com.project.task_manager.enums.UserRole;
import com.project.task_manager.repositories.UserRepository;
import com.project.task_manager.utils.exceptionUtil.GlobalExceptionHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for AuthService.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            logger.info("New admin account got created.");
        } else {
            logger.info("Admin account already exist");
        }
    }

    @Override
    public UserDto signupUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CONTRIBUTOR);
        User createdUser = userRepository.save(user);
        logger.info("New contributor account created.");
        return createdUser.getUserDto();
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
