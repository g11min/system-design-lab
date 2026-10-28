package com.g11.systemdesign.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

// 由容器管理的数据访问组件
@Repository 
public class ProjectRepository {
    
    public Map<String, Object> findById(long id) {
        return Map.of(
            "id", id,
            "name", "Abuja School Project",
            "status", "PLANNING"
        );
    }
}
