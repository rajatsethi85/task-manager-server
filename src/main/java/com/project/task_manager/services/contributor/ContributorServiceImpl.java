package com.project.task_manager.services.contributor;

import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.entities.Task;
import com.project.task_manager.entities.User;
import com.project.task_manager.repositories.TaskRepository;
import com.project.task_manager.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
    public List<TaskDto> getTasksByUserId() {
        User user = jwtUtil.getLoggedInUser();
        if (user != null) {
            return taskRepository.findAllByUser_id(user.getId()).stream()
                    .sorted(Comparator.comparing(Task::getDueDate).reversed())
                    .map(Task::getTaskDto)
                    .collect(Collectors.toList());
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
