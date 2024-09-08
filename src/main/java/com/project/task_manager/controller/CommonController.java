package com.project.task_manager.controller;

import com.project.task_manager.dto.CommentDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.services.CommonService;
import com.project.task_manager.utils.exceptionUtil.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for common crud operations for admin and contributor.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {

    private final CommonService commonService;

    /**
     * Get Task by id.
     * @param id Task id.
     * @return TaskDto.
     */
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto taskDto = commonService.getTaskById(id);
        if (taskDto != null) {
            return ResponseEntity.ok(taskDto);
        }
        throw new DataNotFoundException("Not task found with this id");
    }

    /**
     * Create a comment on a task.
     * @param taskId Task id.
     * @param content comment content.
     * @return CommentDto.
     */
    @PostMapping("/task/comment/{taskId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long taskId, @RequestBody CommentDto content) {
        CommentDto commentDto = commonService.createComment(taskId, content.getContent());
        if (commentDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    /**
     * Get all comments by task id.
     * @param taskId Task id.
     * @return List<CommentDto>.
     */
    @GetMapping("/task/comment/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentsTaskById(@PathVariable Long taskId) {
        List<CommentDto> commentList = commonService.getCommentsByTaskId(taskId);
        if (commentList.isEmpty()) {
            throw new DataNotFoundException("No comments for this task");
        }
        return ResponseEntity.ok(commentList);
    }
}
