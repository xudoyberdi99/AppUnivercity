package com.example.repository;

import com.example.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal,Integer> {

    boolean existsByNameAndGroupId(String name, Integer group_id);
    List<Journal> findAllByGroup_Faculty_UnivercityId(Integer group_faculty_univercity_id);
    List<Journal> findAllByGroup_FacultyId(Integer group_faculty_id);

}
