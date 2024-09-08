package com.project.task_manager.controller.Contributor;

import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.services.contributor.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to do crud operation for CONTRIBUTOR role.
 */
@RestController
@RequestMapping("/api/contributor")
@RequiredArgsConstructor
public class ContributorController {

    private final ContributorService contributorService;

    /**
     * Get all Tasks by user id.
     * @return List<TaskDto>.
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId() {
        return ResponseEntity.ok(contributorService.getTasksByUserId());
    }

    /**
     * Update task by id.
     * @return TaskDto.
     */
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTaskById(@PathVariable Long id, @RequestParam(name = "status") String status) {
        TaskDto taskDto = contributorService.updateTask(id, status);
        if (taskDto != null) {
            return ResponseEntity.ok(taskDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
