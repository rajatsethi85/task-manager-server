package com.project.task_manager.services.contributor;

import com.project.task_manager.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for ContributorService.
 */
public interface ContributorService {

    List<TaskDto> getTasksByUserId();

    TaskDto updateTask(Long id, String status);
}
