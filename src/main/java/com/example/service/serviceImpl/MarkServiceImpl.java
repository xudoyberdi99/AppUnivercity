package com.example.service.serviceImpl;

import com.example.entity.Journal;
import com.example.entity.Mark;
import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.MarkDto;
import com.example.repository.JournalRepository;
import com.example.repository.MarkRepository;
import com.example.repository.StudentRepository;
import com.example.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    MarkRepository markRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    JournalRepository journalRepository;

    @Override
    public List<Mark> getAllMark() {
        return markRepository.findAll();
    }

    @Override
    public Mark getByMarkId(Integer id) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        return optionalMark.orElseGet(Mark::new);
    }

    @Override
    public List<Mark> getAllbyUnivercityIdMark(Integer universityId) {
        return null;
    }

    @Override
    public List<Mark> getAllbyFacultyIdMark(Integer facultyId) {
        return null;
    }

    @Override
    public List<Mark> getAllbyGroupIdMark(Integer groupId) {
        return null;
    }

    @Override
    public ApiResponse addMark(MarkDto markDto) {
        List<Student> students=new ArrayList<>();
        for (Integer integer : markDto.getStudentList()) {
            Student student = studentRepository.getById(integer);
            students.add(student);
        }
        List<Journal> journals=new ArrayList<>();
        for (Integer integer : markDto.getJournalList()) {
            Journal journal = journalRepository.getById(integer);
            journals.add(journal);
        }

        Mark mark=new Mark();
        mark.setBall(markDto.getBall());
        mark.setStudents(students);
        mark.setJournals(journals);

        markRepository.save(mark);
        return new ApiResponse("succes",true);
    }

    @Override
    public ApiResponse editMark(Integer id, MarkDto markDto) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (!markOptional.isPresent()){
            return new ApiResponse("not found mark",false);
        }
        List<Student> students=new ArrayList<>();
        for (Integer integer : markDto.getStudentList()) {
            Student student = studentRepository.getById(integer);
            students.add(student);
        }
        List<Journal> journals=new ArrayList<>();
        for (Integer integer : markDto.getJournalList()) {
            Journal journal = journalRepository.getById(integer);
            journals.add(journal);
        }
        Mark mark = markOptional.get();
        mark.setBall(markDto.getBall());
        mark.setStudents(students);
        mark.setJournals(journals);

        markRepository.save(mark);
        return new ApiResponse("succes edit mark",true);
    }

    @Override
    public ApiResponse deleteMark(Integer id) {
        try {
            markRepository.deleteById(id);
            return new ApiResponse("mark delete succes",true);
        }catch (Exception e){
        return new ApiResponse("Error",false);
        }
    }
}
