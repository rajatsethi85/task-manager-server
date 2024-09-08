package com.project.task_manager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * DTO class for comment request body.
 */
@Data
public class CommentDto {
    private Long id;

    @NotNull(message = "Content can not be null")
    private String content;

    private Date createdAt;

    private Long taskId;

    private Long userId;

    private String postedBy;
}
