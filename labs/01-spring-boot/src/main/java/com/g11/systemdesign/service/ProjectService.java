package com.g11.systemdesign.service;

import com.g11.systemdesign.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProjectService {
    // 构造器依赖注入（Constructor Injection）
    private final ProjectRepository repository;
    private final AtomicInteger requestCount = new AtomicInteger(0);

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
        System.out.println("ProjectService created");
    }

    public Map<String, Object> getProject(long id) {
        return repository.findById(id);
    }

    public int incrementCount() {
        return requestCount.incrementAndGet();
    }
}