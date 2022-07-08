package com.example.service.serviceImpl;

import com.example.entity.Group;
import com.example.entity.Subject;
import com.example.payload.ApiResponse;
import com.example.payload.SubjectDto;
import com.example.repository.GroupRepository;
import com.example.service.SubjectService;
import com.example.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GroupRepository groupRepository;

    @Override
    public ApiResponse addSubject(SubjectDto subjectDto) {
        boolean existsByName = subjectRepository.existsByName(subjectDto.getName());
        if (existsByName) {
            return new ApiResponse("This subject already exist", false);
        }
        List<Group> groups=new ArrayList<>();
        for (Integer integer : subjectDto.getGroupList()) {
            Group group = groupRepository.getById(integer);
            groups.add(group);
        }
        Subject subject=new Subject();
        subject.setName(subjectDto.getName());
        subject.setGroup(groups);

        subjectRepository.save(subject);
        return new ApiResponse("add subject succes",true);
    }

    @Override
    public ApiResponse editSubject(Integer id, SubjectDto subjectDto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()){
            return new ApiResponse("not found subject",false);
        }
        List<Group> groups=new ArrayList<>();
        for (Integer integer : subjectDto.getGroupList()) {
            Group group = groupRepository.getById(integer);
            groups.add(group);
        }
        Subject subject = optionalSubject.get();
        subject.setName(subjectDto.getName());
        subject.setGroup(groups);
        subjectRepository.save(subject);
        return new ApiResponse("edit subject ",true);
    }

    @Override
    public ApiResponse deleteSubject(Integer id) {
        try {
            subjectRepository.deleteById(id);
            return new ApiResponse("delete subject succes",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getBySubjectId(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElse(new Subject());
    }

}
