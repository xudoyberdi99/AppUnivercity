package com.example.service;

import com.example.entity.Mark;
import com.example.payload.ApiResponse;
import com.example.payload.MarkDto;

import java.util.List;

public interface MarkService {
    List<Mark> getAllMark();

    Mark getByMarkId(Integer id);

    List<Mark> getAllbyUnivercityIdMark(Integer universityId);

    List<Mark> getAllbyFacultyIdMark(Integer facultyId);

    List<Mark> getAllbyGroupIdMark(Integer groupId);

    ApiResponse addMark(MarkDto markDto);

    ApiResponse editMark(Integer id, MarkDto markDto);

    ApiResponse deleteMark(Integer id);
}
