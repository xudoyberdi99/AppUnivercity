package com.example.controller;

import com.example.entity.Group;
import com.example.entity.Subject;
import com.example.entity.Univercity;
import com.example.payload.ApiResponse;
import com.example.payload.SubjectDto;
import com.example.payload.UnivercityDto;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping
    public HttpEntity<?> getAllSubjects(){
        List<Subject> subjects=subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getBySubjectId(@PathVariable Integer id){
        Subject subject=subjectService.getBySubjectId(id);
        return ResponseEntity.ok(subject);
    }


    @PostMapping
    public HttpEntity<?> addSubject(@Valid @RequestBody SubjectDto subjectDto){
        ApiResponse apiResponse=subjectService.addSubject(subjectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editSubject(@Valid @PathVariable Integer id, @RequestBody SubjectDto subjectDto){
        ApiResponse apiResponse=subjectService.editSubject(id,subjectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSubject(@PathVariable Integer id){
        ApiResponse apiResponse=subjectService.deleteSubject(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }



}
