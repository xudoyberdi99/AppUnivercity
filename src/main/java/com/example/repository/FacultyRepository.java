package com.example.repository;

import com.example.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    boolean existsByNameAndUnivercityId(String name, Integer univercity_id);

    List<Faculty> findAllByUnivercityId(Integer university_id);
}
