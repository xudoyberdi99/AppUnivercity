package com.example.service.serviceImpl;

import com.example.entity.Faculty;
import com.example.entity.Univercity;
import com.example.payload.ApiResponse;
import com.example.payload.FacultyDto;
import com.example.payload.SubjectDto;
import com.example.repository.FacultyRepository;
import com.example.repository.UnivercityRepository;
import com.example.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UnivercityRepository univercityRepository;

    // hamma fakultetlar ruyxati
    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getByFacultyId(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        return optionalFaculty.orElse(new Faculty());
    }
        // Universitet id si buyicha fakultetlar
    @Override
    public List<Faculty> getAllbyUnivercityIdFaculty(Integer universityId) {
        return facultyRepository.findAllByUnivercityId(universityId);
    }

    @Override
    public ApiResponse addFaculty(FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUnivercityId(facultyDto.getName(), facultyDto.getUnivercityId());
        if (exists){
            return new ApiResponse("this univercity such faculty",false);
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<Univercity> optionalUnivercity = univercityRepository.findById(facultyDto.getUnivercityId());
        if (!optionalUnivercity.isPresent()){
            return new ApiResponse("not found univercity",false);
        }
        faculty.setUnivercity(optionalUnivercity.get());

        facultyRepository.save(faculty);
        return new ApiResponse("add faculty succes", true);
    }

    @Override
    public ApiResponse editFaculty(Integer id, FacultyDto  facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (!optionalFaculty.isPresent()){
            return new ApiResponse("not found Faculty", false);
        }
        Faculty faculty = optionalFaculty.get();
        faculty.setName(facultyDto.getName());
        Optional<Univercity> optionalUnivercity = univercityRepository.findById(facultyDto.getUnivercityId());
        if (!optionalUnivercity.isPresent()){
            return new ApiResponse("not found univercity",false);
        }
        faculty.setUnivercity(optionalUnivercity.get());

        facultyRepository.save(faculty);
        return new ApiResponse("edit faculty succes",true);
    }

    @Override
    public ApiResponse deleteFaculty(Integer id) {
        try {
            facultyRepository.deleteById(id);
            return new ApiResponse("delete faulty succes ",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
