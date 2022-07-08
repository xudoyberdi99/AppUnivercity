package com.example.service.serviceImpl;

import com.example.entity.Group;
import com.example.entity.Journal;
import com.example.entity.Subject;
import com.example.payload.ApiResponse;
import com.example.payload.JournalDto;
import com.example.repository.GroupRepository;
import com.example.repository.JournalRepository;
import com.example.repository.SubjectRepository;
import com.example.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Journal> getAllJournal() {
        return journalRepository.findAll();
    }

    @Override
    public Journal getByJournalId(Integer id) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        return optionalJournal.orElse(new Journal());
    }

    @Override
    public List<Journal> getAllbyUnivercityIdJournals(Integer universityId) {
        return journalRepository.findAllByGroup_Faculty_UnivercityId(universityId);
    }

    @Override
    public List<Journal> getAllbyFacultyIdJournal(Integer facultyId) {
        return journalRepository.findAllByGroup_FacultyId(facultyId);
    }

    @Override
    public ApiResponse addJournal(JournalDto journalDto) {
        boolean exists = journalRepository.existsByNameAndGroupId(journalDto.getName(), journalDto.getGroupId());
        if (exists){
            return new ApiResponse("Journal already exists",false);
        }
        Optional<Group> optionalGroup = groupRepository.findById(journalDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }
        Set<Subject> subjects=new HashSet<>();
        for (Integer integer : journalDto.getSubjectsList()) {
            Subject subject = subjectRepository.getById(integer);
            subjects.add(subject);
        }
        Journal journal=new Journal();
        journal.setName(journalDto.getName());
        journal.setGroup(optionalGroup.get());
        journal.setSubjects(subjects);

        journalRepository.save(journal);
        return new ApiResponse("add succes Journal",true);
    }

    @Override
    public ApiResponse editJournal(Integer id, JournalDto journalDto) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        if (!optionalJournal.isPresent()){
            return new ApiResponse("not found journal",false);
        }
        Optional<Group> optionalGroup = groupRepository.findById(journalDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }
        Set<Subject> subjects=new HashSet<>();
        for (Integer integer : journalDto.getSubjectsList()) {
            Subject subject = subjectRepository.getById(integer);
            subjects.add(subject);
        }
        Journal journal = optionalJournal.get();
        journal.setName(journalDto.getName());
        journal.setGroup(optionalGroup.get());
        journal.setSubjects(subjects);

        journalRepository.save(journal);

        return new ApiResponse("edit Journal succes",true);
    }

    @Override
    public ApiResponse deleteJournal(Integer id) {
        try {
            journalRepository.deleteById(id);
            return new ApiResponse("delete journal succes",true);
        }catch (Exception e){
        return new ApiResponse("Error",false);
        }
    }
}
