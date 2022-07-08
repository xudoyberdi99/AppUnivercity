package com.example.service;

import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.StudentDto;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    List<Student> getAllbyUnivercityIdStudent(Integer universityId);

    List<Student> getAllbyFacultyIdStudent(Integer facultyId);

    List<Student> getAllbyGroupIdStudent(Integer groupId);

    Student getByStudenId(Integer id);

    ApiResponse addStudent(StudentDto studentDto);

    ApiResponse editStudent(Integer id, StudentDto studentDto);

    ApiResponse deleteStudent(Integer id);

    Student getByStudentName(String fullName);

    List<Object> getByStudentSubject(Integer id);

    Integer CountStudentFacultyId(Integer facultyId);
}
