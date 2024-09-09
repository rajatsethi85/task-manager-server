package com.project.task_manager.repositories;

import com.project.task_manager.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for Task table in DB.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<?> findAllByUser_id(Long id, Pageable pageable);
}
