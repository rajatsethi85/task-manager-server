package com.project.task_manager.services;

import com.project.task_manager.dto.CommentDto;
import com.project.task_manager.dto.TaskDto;

import java.util.List;

/**
 * Interface for CommonService.
 */
public interface CommonService {

    CommentDto createComment(Long taskId, String content);

    List<CommentDto> getCommentsByTaskId(Long taskId);

    TaskDto getTaskById(Long id);

}
