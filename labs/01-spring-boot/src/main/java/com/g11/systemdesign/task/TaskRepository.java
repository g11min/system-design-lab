package com.g11.systemdesign.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<AnalysisTask, Long> {
    
}