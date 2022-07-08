package com.example.service.serviceImpl;

import com.example.entity.Faculty;
import com.example.entity.Group;
import com.example.payload.ApiResponse;
import com.example.payload.GroupDto;
import com.example.repository.FacultyRepository;
import com.example.repository.GroupRepository;
import com.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    // hamma gruppalar ruyxati
    @Override
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    @Override
    public Group getByGroupId(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElse(new Group());
    }

    //Universitet Id si buyicha grouplar ruyxati
    @Override
    public List<Group> getAllbyUnivercityIdGroup(Integer universityId) {
        return groupRepository.findAllByFaculty_UnivercityId(universityId);
    }

    @Override
    public List<Group> getAllbyfacultyIdGroup(Integer facultyId) {
        return groupRepository.findAllByFacultyId(facultyId);
    }

    @Override
    public ApiResponse addGroup(GroupDto groupDto) {
        boolean exists = groupRepository.existsByNameAndFacultyId(groupDto.getName(), groupDto.getFacultyId());
        if (exists){
            return new ApiResponse("already exists group",false);
        }
        Group group=new Group();
        group.setName(groupDto.getName());
        group.setYear(groupDto.getYear());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return new ApiResponse("not found faculty",false);
        }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return new ApiResponse("add group succes",true);
    }

    @Override
    public ApiResponse editGroup(Integer id, GroupDto groupDto) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }
        Group group = optionalGroup.get();
        group.setName(group.getName());
        group.setYear(groupDto.getYear());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return new ApiResponse("not found faculty",false);
        }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return new ApiResponse("edit group succes ",true);
    }

    @Override
    public ApiResponse deleteGroup(Integer id) {
        try {
            groupRepository.deleteById(id);
            return new ApiResponse("Delete group succes",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }

    }

    @Override
    public Integer AllbyfacultyIdGroup(Integer facultyId) {
        return groupRepository.countAllByFacultyId(facultyId);
    }
}
