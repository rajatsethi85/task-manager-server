package com.project.task_manager.repositories;

import com.project.task_manager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for Task table in DB.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUser_id(Long id);
}
