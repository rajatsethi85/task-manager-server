package com.project.task_manager.controller;

import com.project.task_manager.dto.*;
import com.project.task_manager.entities.User;
import com.project.task_manager.repositories.UserRepository;
import com.project.task_manager.services.auth.AuthService;
import com.project.task_manager.services.jwt.UserService;
import com.project.task_manager.utils.CommonUtils;
import com.project.task_manager.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Rest controller for login and signup operations with jwt token.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CommonUtils commonUtils;

    /**
     * API to sign up a new contributor user.
     * @param signupRequest
     * @return ResponseEntity.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) throws Exception {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist with this email");
        }
        String decodedPassword = commonUtils.decodePassword(signupRequest.getPassword());
        signupRequest.setPassword(decodedPassword);
        UserDto userDto = authService.signupUser(signupRequest);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    /**
     * API to login a user.
     * @param authRequest
     * @return AuthResponse.
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
        String decodedPassword = commonUtils.decodePassword(authRequest.getPassword());
        authRequest.setPassword(decodedPassword);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authRequest.getEmail());
        Optional<User> user = userRepository.findFirstByEmail(authRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse();
        if (user.isPresent()) {
            authResponse.setJwt(token);
            authResponse.setUserId(user.get().getId());
            authResponse.setUserRole(user.get().getUserRole());
            authResponse.setName(user.get().getName());
        }
        return authResponse;
    }
}
