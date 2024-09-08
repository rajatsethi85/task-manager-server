package com.project.task_manager.services.admin;

import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.dto.UserDto;
import com.project.task_manager.entities.Task;
import com.project.task_manager.entities.User;
import com.project.task_manager.enums.UserRole;
import com.project.task_manager.repositories.TaskRepository;
import com.project.task_manager.repositories.UserRepository;
import com.project.task_manager.utils.exceptionUtil.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for AdminService.
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    public List<UserDto> getUsers() {
        logger.info("Fetching all users from db");
        return userRepository.findAllByUserRole(UserRole.CONTRIBUTOR)
                .stream()
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        logger.info("Creating new task");
        Optional<User> optionalUser = userRepository.findById(taskDto.getContributorId());
        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(taskDto.getDueDate());
            task.setTaskStatus("ASSIGNED");
            task.setUser(optionalUser.get());
            logger.info("New task created");
            return taskRepository.save(task).getTaskDto();
        }
        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll()
                .stream().sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        logger.info("Task deleted with id: " + id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        logger.info("Getting task with id: " + id);
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDto).orElse(null);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        logger.info("Updating task with id: " + id);
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription((taskDto.getDescription()));
            existingTask.setDueDate(taskDto.getDueDate());
            existingTask.setPriority(taskDto.getPriority());
            existingTask.setTaskStatus(taskDto.getTaskStatus());
            logger.info("Task updated with id: " + id);
            return taskRepository.save(existingTask).getTaskDto();
        }
        return null;
    }
}
