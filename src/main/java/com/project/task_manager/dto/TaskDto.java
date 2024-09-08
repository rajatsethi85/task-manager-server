package com.project.task_manager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * DTO class for Task request body.
 */
@Data
public class TaskDto {
    private Long id;
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "DueDate cannot be null")
    private Date dueDate;

    @NotNull(message = "Priority cannot be null")
    private String priority;

    @NotNull(message = "TaskStatus cannot be null")
    private String taskStatus;

    private String contributorName;

    @NotNull(message = "Contributor id cannot be null")
    private Long contributorId;
}
