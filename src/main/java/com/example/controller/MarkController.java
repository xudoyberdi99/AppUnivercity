package com.example.controller;

import com.example.entity.Group;
import com.example.entity.Mark;
import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.GroupDto;
import com.example.payload.MarkDto;
import com.example.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
    @Autowired
    MarkService markService;

    @GetMapping
    public HttpEntity<?> getAllMark(){
        List<Mark> marks=markService.getAllMark();
        return ResponseEntity.ok(marks);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByMarkId(@PathVariable Integer id){
        Mark mark=markService.getByMarkId(id);
        return ResponseEntity.ok(mark);
    }

    @GetMapping("/byUniversityId/{universityId}")
    public HttpEntity<?> getAllbyUnivercityIdMark(@PathVariable Integer universityId){
        List<Mark> markList = markService.getAllbyUnivercityIdMark(universityId);
        return ResponseEntity.ok(markList);
    }
    @GetMapping("/forFaculty/{facultyId}")
    public HttpEntity<?> getAllbyFacultyIdMark(@PathVariable Integer facultyId){
        List<Mark> marks = markService.getAllbyFacultyIdMark(facultyId);
        return ResponseEntity.ok(marks);
    }
    @GetMapping("/forGroup/{groupId}")
    public HttpEntity<?> getAllbyGroupIdMark(@PathVariable Integer groupId){
        List<Mark> marks = markService.getAllbyGroupIdMark(groupId);
        return ResponseEntity.ok(marks);
    }

    @PostMapping
    public HttpEntity<?> addMark(@Valid @RequestBody MarkDto markDto){
        ApiResponse apiResponse=markService.addMark(markDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editMark(@Valid @PathVariable Integer id, @RequestBody MarkDto markDto){
        ApiResponse apiResponse=markService.editMark(id,markDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMark(@PathVariable Integer id){
        ApiResponse apiResponse=markService.deleteMark(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
