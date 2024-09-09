package com.project.task_manager.services.contributor;

import com.project.task_manager.dto.PagedResponseDto;
import com.project.task_manager.dto.TaskDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for ContributorService.
 */
public interface ContributorService {

    PagedResponseDto getTasksByUserId(int pageNumber, int pageSize);

    TaskDto updateTask(Long id, String status);
}
