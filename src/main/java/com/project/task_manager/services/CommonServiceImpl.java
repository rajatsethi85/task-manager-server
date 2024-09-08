package com.project.task_manager.services;

import com.project.task_manager.dto.CommentDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.entities.Comment;
import com.project.task_manager.entities.Task;
import com.project.task_manager.entities.User;
import com.project.task_manager.repositories.CommentRepository;
import com.project.task_manager.repositories.TaskRepository;
import com.project.task_manager.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for CommonService.
 */
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    @Override
    public CommentDto createComment(Long taskId, String content) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        User user = jwtUtil.getLoggedInUser();
        if (optionalTask.isPresent() && user != null) {
            Comment comment = new Comment();
            comment.setCreatedAt(new Date());
            comment.setUser(user);
            comment.setTask(optionalTask.get());
            comment.setContent(content);
            return commentRepository.save(comment).getCommentDto();
        }
        throw new EntityNotFoundException("User or Task not found");
    }

    @Override
    public List<CommentDto> getCommentsByTaskId(Long taskId) {
        return commentRepository.findAllByTaskId(taskId).stream().map(Comment::getCommentDto)
                .sorted(Comparator.comparing(CommentDto::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDto).orElse(null);
    }
}
