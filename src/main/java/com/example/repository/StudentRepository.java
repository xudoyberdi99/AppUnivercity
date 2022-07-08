package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByGroup_Faculty_UnivercityId(Integer group_faculty_univercity_id);
    List<Student> findAllByGroup_FacultyId(Integer group_faculty_id);
    List<Student> findAllByGroupId(Integer group_id);

    @Query(value = "select st.full_name,sb.name from student st  join groups g on g.id=st.id join subject sb on sb.id=g.id\n" +
            "where st.id=:id",nativeQuery = true)
    List<Object> studentsSubjects(Integer id);

    Optional<Student> findByFullName(String fullName);

    Integer countAllByGroup_FacultyId(Integer group_faculty_id);

}
