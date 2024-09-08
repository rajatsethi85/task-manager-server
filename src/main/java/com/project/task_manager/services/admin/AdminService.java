package com.project.task_manager.services.admin;

import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.dto.UserDto;

import java.util.List;

/**
 * Interface for AdminService.
 */
public interface AdminService {
    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

}
