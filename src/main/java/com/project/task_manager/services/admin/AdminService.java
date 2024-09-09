package com.project.task_manager.services.admin;

import com.project.task_manager.dto.PagedResponseDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.dto.UserDto;

import java.util.List;

/**
 * Interface for AdminService.
 */
public interface AdminService {
    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    PagedResponseDto getAllTasks(int pageNumber, int pageSize);

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

}
