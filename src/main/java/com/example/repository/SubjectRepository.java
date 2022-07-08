package com.example.repository;

import com.example.entity.Group;
import com.example.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);
}
