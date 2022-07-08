package com.example.repository;

import com.example.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    boolean existsByNameAndFacultyId(String name, Integer faculty_id);

    List<Group> findAllByFaculty_UnivercityId(Integer faculty_university_id);

    List<Group> findAllByFacultyId(Integer faculty_id);

    Integer countAllByFacultyId(Integer faculty_id);


}
