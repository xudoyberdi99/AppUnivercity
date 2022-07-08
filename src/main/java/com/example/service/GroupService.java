package com.example.service;

import com.example.entity.Group;
import com.example.payload.ApiResponse;
import com.example.payload.GroupDto;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroup();

    Group getByGroupId(Integer id);

    ApiResponse addGroup(GroupDto groupDto);

    ApiResponse editGroup(Integer id, GroupDto groupDto);

    ApiResponse deleteGroup(Integer id);

    List<Group> getAllbyUnivercityIdGroup(Integer universityId);

    List<Group> getAllbyfacultyIdGroup(Integer facultyId);

    Integer AllbyfacultyIdGroup(Integer facultyId);
}
