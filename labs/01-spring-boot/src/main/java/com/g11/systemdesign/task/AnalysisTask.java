package com.g11.systemdesign.task;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "analysis_task")
public class AnalysisTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long fileId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING;

    @Column(nullable = false)
    private int attempts = 0;

    @Column(nullable = false)
    private int maxAttempts = 3;

    private Instant createdAt;

    protected AnalysisTask() {
    }

    public AnalysisTask(Long fileId) {
        this.fileId = fileId;
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Long getFileId() {
        return fileId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
