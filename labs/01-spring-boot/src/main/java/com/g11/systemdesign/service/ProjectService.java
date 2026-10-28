package com.g11.systemdesign.service;

import com.g11.systemdesign.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ProjectService {
    // 构造器依赖注入（Constructor Injection）
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
        System.out.println("ProjectService created");
    }

    public Map<String, Object> getProject(long id) {
        return repository.findById(id);
    }
}