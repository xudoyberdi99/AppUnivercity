package com.example.controller;

import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.StudentDto;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public HttpEntity<?> getAllStudent(){
        List<Student> studentList=studentService.getAllStudent();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/forUniversityId/{universityId}")
    public HttpEntity<?> getAllbyUnivercityIdStudent(@PathVariable Integer universityId){
        List<Student> students = studentService.getAllbyUnivercityIdStudent(universityId);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/forFaculty/{facultyId}")
    public HttpEntity<?> getAllbyFacultyIdStudent(@PathVariable Integer facultyId){
        List<Student> students = studentService.getAllbyFacultyIdStudent(facultyId);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/forGroup/{groupId}")
    public HttpEntity<?> getAllbyGroupIdStudent(@PathVariable Integer groupId){
        List<Student> students = studentService.getAllbyGroupIdStudent(groupId);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByStudenId(@PathVariable Integer id){
        Student student=studentService.getByStudenId(id);
        return ResponseEntity.ok(student);
    }
    @PostMapping
    public HttpEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto){
        ApiResponse apiResponse=studentService.addStudent(studentDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editStudent(@Valid @PathVariable Integer id, @RequestBody StudentDto studentDto){
        ApiResponse apiResponse=studentService.editStudent(id,studentDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStudent(@PathVariable Integer id){
        ApiResponse apiResponse=studentService.deleteStudent(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    //Student id buyicha talaba uqiydigan fanlar
    @GetMapping("/studentsubjects/{id}")
    public HttpEntity<?> getByStudentSubject(@PathVariable Integer id){
        List<Object> objects=studentService.getByStudentSubject(id);
        return ResponseEntity.ok(objects);
    }
    //talaba ismi buyicha malumotlar
    @GetMapping("/studentDate")
    public HttpEntity<?> getByStudentName(@RequestParam String fullName){
        Student student=studentService.getByStudentName(fullName);
        return ResponseEntity.ok(student);
    }
    //Fakultet Id buyicha talabalar soni
    @GetMapping("/CountStudentFacultyId/{facultyId}")
    public HttpEntity<?> CountStudentFacultyId(@PathVariable Integer facultyId){
        Integer integer=studentService.CountStudentFacultyId(facultyId);
        return ResponseEntity.ok(integer);
    }
}
