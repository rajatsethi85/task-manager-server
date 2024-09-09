package com.project.task_manager.controller.Contributor;

import com.project.task_manager.dto.PagedResponseDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.services.contributor.ContributorService;
import com.project.task_manager.utils.exceptionUtil.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
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
     * Get all Tasks by user id based on pagination.
     * @param pageNumber page number
     * @param  pageSize page size
     * @return PagesResponseDto.
     */
    @GetMapping("/tasks")
    public ResponseEntity<PagedResponseDto> getTasksByUserId(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                             @RequestParam(defaultValue = "6", required = false) int pageSize) {
        PagedResponseDto tasks = contributorService.getTasksByUserId(pageNumber, pageSize);
        if (tasks.getTotalElements() == 0) {
            throw new DataNotFoundException("No Tasks found");
        }
        return ResponseEntity.ok(tasks);
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
