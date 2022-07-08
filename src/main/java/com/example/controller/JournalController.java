package com.example.controller;

import com.example.entity.Group;
import com.example.entity.Journal;
import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.GroupDto;
import com.example.payload.JournalDto;
import com.example.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalController {
    @Autowired
    JournalService journalService;

    @GetMapping
    public HttpEntity<?> getAllJournal(){
        List<Journal> journals=journalService.getAllJournal();
        return ResponseEntity.ok(journals);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getByJournalId(@PathVariable Integer id){
        Journal journal=journalService.getByJournalId(id);
        return ResponseEntity.ok(journal);
    }

    @GetMapping("/forUniversityId/{universityId}")
    public HttpEntity<?> getAllbyUnivercityIdJournals(@PathVariable Integer universityId){
        List<Journal> journals = journalService.getAllbyUnivercityIdJournals(universityId);
        return ResponseEntity.ok(journals);
    }

    @GetMapping("/forFaculty/{facultyId}")
    public HttpEntity<?> getAllbyFacultyIdJournal(@PathVariable Integer facultyId){
        List<Journal> journals = journalService.getAllbyFacultyIdJournal(facultyId);
        return ResponseEntity.ok(journals);
    }

    @PostMapping
    public HttpEntity<?> addJournal(@Valid @RequestBody JournalDto journalDto){
        ApiResponse apiResponse=journalService.addJournal(journalDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editJournal(@Valid @PathVariable Integer id, @RequestBody JournalDto journalDto){
        ApiResponse apiResponse=journalService.editJournal(id,journalDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteJournal(@PathVariable Integer id){
        ApiResponse apiResponse=journalService.deleteJournal(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
