package com.project.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO to return info for pagination.
 */
@Data
@AllArgsConstructor
public class PagedResponseDto {

    private long totalElements;
    private int totalPages;
    private List<?> data;
}
