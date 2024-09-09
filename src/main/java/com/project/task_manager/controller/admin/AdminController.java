package com.project.task_manager.controller.admin;

import com.project.task_manager.dto.PagedResponseDto;
import com.project.task_manager.dto.TaskDto;
import com.project.task_manager.dto.UserDto;
import com.project.task_manager.services.admin.AdminService;
import com.project.task_manager.utils.exceptionUtil.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to do admin role based crud operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * Get all contributor users.
     * @return List<UserDto>.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> usersList = adminService.getUsers();
        if (usersList.isEmpty()) {
            throw new DataNotFoundException("No users found");
        }
        return ResponseEntity.ok(usersList);
    }


    /**
     * Create a new task.
     * @param taskDto Task DTO
     * @return TaskDto.
     */
    @RequestMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTaskDto = adminService.createTask(taskDto);
        if (createdTaskDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status((HttpStatus.CREATED)).body(createdTaskDto);
    }

    /**
     * Get all Tasks for admin based on pagination.
     * @param pageNumber page number
     * @param  pageSize page size
     * @return PagesResponseDto.
     */
    @GetMapping("/tasks")
    public ResponseEntity<PagedResponseDto> getAllTasks(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                        @RequestParam(defaultValue = "6", required = false) int pageSize) {
        PagedResponseDto tasks = adminService.getAllTasks(pageNumber, pageSize);
        if (tasks.getTotalElements() == 0) {
            throw new DataNotFoundException("No Tasks found");
        }
        return ResponseEntity.ok(tasks);
    }

    /**
     * Delete Task by id.
     * @return void.
     */
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        adminService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get task by id.
     * @return UserDto.
     */
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto taskDto = adminService.getTaskById(id);
        if (taskDto == null) {
            throw new DataNotFoundException("No Task found with id: " + id);
        }
        return ResponseEntity.ok(taskDto);
    }

    /**
     * Update task by id.
     * @return TaskDto.
     */
    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = adminService.updateTask(id, taskDto);
        if (updatedTask == null) throw new DataNotFoundException("Not able to update. No Task found with id: " + id);

        return ResponseEntity.ok(updatedTask);
    }

}
