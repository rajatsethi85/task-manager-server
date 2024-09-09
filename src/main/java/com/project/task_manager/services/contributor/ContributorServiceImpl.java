package com.project.task_manager.services.contributor;

import com.project.task_manager.dto.PagedResponseDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.entities.Task;
import com.project.task_manager.entities.User;
import com.project.task_manager.repositories.TaskRepository;
import com.project.task_manager.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for ContributorService.
 */
@Service
@RequiredArgsConstructor
public class ContributorServiceImpl implements ContributorService {
    private final TaskRepository taskRepository;
    private final JwtUtil jwtUtil;

    @Override
    public PagedResponseDto getTasksByUserId(int pageNumber, int pageSize) {
        User user = jwtUtil.getLoggedInUser();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if(user != null) {
            Page<?> page =  taskRepository.findAllByUser_id(user.getId(), pageable);
            int totalPages = page.getTotalPages();
            long totalElements = page.getTotalElements();
            List<Task> taskList = (List<Task>) page.getContent();
            List<TaskDto> taskDtoList = taskList.stream().sorted(Comparator.comparing(Task::getDueDate).reversed())
                    .map(Task::getTaskDto)
                    .toList();
            return new PagedResponseDto(totalElements, totalPages, taskDtoList);
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public TaskDto updateTask(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTaskStatus(status);
            return taskRepository.save(existingTask).getTaskDto();
        }
        throw new EntityNotFoundException("Task not found");

    }
}
