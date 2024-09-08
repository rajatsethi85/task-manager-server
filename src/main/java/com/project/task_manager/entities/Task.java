package com.project.task_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.task_manager.dto.TaskDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * Entity class for Task table in DB.
 */
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private String taskStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public TaskDto getTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(id);
        taskDto.setTitle(title);
        taskDto.setDescription(description);
        taskDto.setContributorName(user.getName());
        taskDto.setContributorId(user.getId());
        taskDto.setTaskStatus(taskStatus);
        taskDto.setPriority(priority);
        taskDto.setDueDate(dueDate);
        return taskDto;
    }
}
