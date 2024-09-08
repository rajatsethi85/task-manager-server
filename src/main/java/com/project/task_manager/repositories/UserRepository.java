package com.project.task_manager.repositories;

import com.project.task_manager.entities.User;
import com.project.task_manager.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for User table in DB.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole userRole);

    List<User> findAllByUserRole(UserRole userRole);
}

