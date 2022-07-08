package com.example.service;

import com.example.entity.Faculty;
import com.example.payload.ApiResponse;
import com.example.payload.FacultyDto;
import com.example.payload.SubjectDto;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAllFaculty();

    Faculty getByFacultyId(Integer id);

    ApiResponse addFaculty(FacultyDto facultyDto);

    ApiResponse editFaculty(Integer id, FacultyDto facultyDto);

    ApiResponse deleteFaculty(Integer id);

    List<Faculty> getAllbyUnivercityIdFaculty(Integer universityId);

}
