package com.example.service;

import com.example.entity.Journal;
import com.example.payload.ApiResponse;
import com.example.payload.JournalDto;

import java.util.List;

public interface JournalService {
    List<Journal> getAllJournal();

    Journal getByJournalId(Integer id);

    List<Journal> getAllbyUnivercityIdJournals(Integer universityId);

    List<Journal> getAllbyFacultyIdJournal(Integer facultyId);

    ApiResponse addJournal(JournalDto journalDto);

    ApiResponse editJournal(Integer id, JournalDto journalDto);

    ApiResponse deleteJournal(Integer id);

}
