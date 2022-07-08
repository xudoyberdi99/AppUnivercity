package com.example.service;

import com.example.entity.Subject;
import com.example.payload.ApiResponse;
import com.example.payload.SubjectDto;

import java.util.List;

public interface SubjectService {
    ApiResponse addSubject(SubjectDto subjectDto);

    ApiResponse editSubject(Integer id, SubjectDto subjectDto);

    ApiResponse deleteSubject(Integer id);

    List<Subject> getAllSubjects();

    Subject getBySubjectId(Integer id);
}
