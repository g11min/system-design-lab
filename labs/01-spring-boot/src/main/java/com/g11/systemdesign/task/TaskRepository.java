package com.g11.systemdesign.task;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<AnalysisTask, Long> {

    @Query(value = """
            SELECT *
            FROM analysis_task
            WHERE status = 'PENDING'
            ORDER BY id
            LIMIT 1
            FOR UPDATE SKIP LOCKED
            """, nativeQuery = true)
    Optional<AnalysisTask> findNextPendingTask();
}