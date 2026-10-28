package com.g11.systemdesign.controller;

import com.g11.systemdesign.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
        System.out.println("ProjectController created");
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProject(@PathVariable long id) {
        return service.getProject(id);
    }

    @GetMapping("/count")
    public int count() {
        return service.incrementCount();
    }
}