package com.example.controller;

import com.example.entity.Faculty;
import com.example.entity.Group;
import com.example.payload.ApiResponse;
import com.example.payload.FacultyDto;
import com.example.payload.GroupDto;
import com.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    public HttpEntity<?> getAllGroup(){
        List<Group> groups=groupService.getAllGroup();
        return ResponseEntity.ok(groups);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByGroupId(@PathVariable Integer id){
        Group group=groupService.getByGroupId(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/byUniversityId/{universityId}")
    public HttpEntity<?> getAllbyUnivercityIdGroup(@PathVariable Integer universityId){
        List<Group> groupList = groupService.getAllbyUnivercityIdGroup(universityId);
        return ResponseEntity.ok(groupList);
    }
    @GetMapping("/forFacultyId/{facultyId}")
    public HttpEntity<?> getAllbyfacultyIdGroup(@PathVariable Integer facultyId){
        List<Group> groupList = groupService.getAllbyfacultyIdGroup(facultyId);
        return ResponseEntity.ok(groupList);
    }

    @PostMapping
    public HttpEntity<?> addGroup(@Valid @RequestBody GroupDto groupDto){
        ApiResponse apiResponse=groupService.addGroup(groupDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editGroup(@Valid @PathVariable Integer id, @RequestBody GroupDto groupDto){
        ApiResponse apiResponse=groupService.editGroup(id,groupDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGroup(@PathVariable Integer id){
        ApiResponse apiResponse=groupService.deleteGroup(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

    @GetMapping("/countforFacultyId/{facultyId}")
    public HttpEntity<?> AllbyfacultyIdGroup(@PathVariable Integer facultyId){
        Integer groupList = groupService.AllbyfacultyIdGroup(facultyId);
        return ResponseEntity.ok(groupList);
    }
}
