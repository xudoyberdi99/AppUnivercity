package com.example.controller;

import com.example.entity.Faculty;
import com.example.entity.Subject;
import com.example.payload.ApiResponse;
import com.example.payload.FacultyDto;
import com.example.payload.SubjectDto;
import com.example.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @GetMapping
    public HttpEntity<?> getAllFaculty(){
        List<Faculty> faculties=facultyService.getAllFaculty();
        return ResponseEntity.ok(faculties);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByFacultyId(@PathVariable Integer id){
        Faculty faculty=facultyService.getByFacultyId(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/byUniversityId/{universityId}")
    public HttpEntity<?> getAllbyUnivercityIdFaculty(@PathVariable Integer universityId){
        List<Faculty> facultyList = facultyService.getAllbyUnivercityIdFaculty(universityId);
        return ResponseEntity.ok(facultyList);
    }

    @PostMapping
    public HttpEntity<?> addFaculty(@Valid @RequestBody FacultyDto facultyDto){
        ApiResponse apiResponse=facultyService.addFaculty(facultyDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editFaculty(@Valid @PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        ApiResponse apiResponse=facultyService.editFaculty(id,facultyDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFaculty(@PathVariable Integer id){
        ApiResponse apiResponse=facultyService.deleteFaculty(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
