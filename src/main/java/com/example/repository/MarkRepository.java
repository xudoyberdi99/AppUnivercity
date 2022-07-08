package com.example.repository;

import com.example.entity.Mark;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark,Integer> {
//    @Query(value = "select s.full_name,m.ball from mark m join student s on s.id=m.id",nativeQuery = true)
//    List<Mark> findAllByStudentsAndBall();

}
