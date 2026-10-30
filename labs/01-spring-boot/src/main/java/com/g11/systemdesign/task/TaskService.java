package com.g11.systemdesign.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AnalysisTask createTask(Long fileId) {

        AnalysisTask task = new AnalysisTask(fileId);

        return repository.save(task);
    }

    @Transactional(readOnly = true)
    public AnalysisTask getTask(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AnalysisTask> getAllTasks() {
        return repository.findAll();
    }
}