package com.g11.systemdesign.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public record CreateTaskRequest(Long fileId) {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnalysisTask createTask(
            @RequestBody CreateTaskRequest request) {

        if (request.fileId() == null ||
                request.fileId() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "fileId must be positive");
        }

        return service.createTask(request.fileId());
    }

    @GetMapping("/{id}")
    public AnalysisTask getTask(@PathVariable Long id) {

        try {
            return service.getTask(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @GetMapping
    public List<AnalysisTask> getAllTasks() {
        return service.getAllTasks();
    }
}