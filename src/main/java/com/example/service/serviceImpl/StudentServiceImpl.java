package com.example.service.serviceImpl;

import com.example.entity.Address;
import com.example.entity.Group;
import com.example.entity.Student;
import com.example.payload.ApiResponse;
import com.example.payload.StudentDto;
import com.example.repository.AddressRepository;
import com.example.repository.GroupRepository;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllbyUnivercityIdStudent(Integer universityId) {
        return studentRepository.findAllByGroup_Faculty_UnivercityId(universityId);
    }

    @Override
    public List<Student> getAllbyFacultyIdStudent(Integer facultyId) {
        return studentRepository.findAllByGroup_FacultyId(facultyId);
    }

    @Override
    public List<Student> getAllbyGroupIdStudent(Integer groupId) {
        return studentRepository.findAllByGroupId(groupId);
    }

    @Override
    public Student getByStudenId(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(new Student());
    }

    @Override
    public ApiResponse addStudent(StudentDto studentDto) {
        Address address = new Address();
        address.setCity(studentDto.getCity());
        address.setDistrict(studentDto.getDistrict());
        address.setStreet(studentDto.getStreet());

        Address savedAddress = addressRepository.save(address);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }
        Student student=new Student();
        student.setAddress(savedAddress);
        student.setFullName(studentDto.getFullName());
        student.setGroup(optionalGroup.get());

        studentRepository.save(student);
        return new ApiResponse("add student succes",true);
    }

    @Override
    public ApiResponse editStudent(Integer id, StudentDto studentDto) {
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setFullName(studentDto.getFullName());
            student.setGroup(optionalGroup.get());

            Address address = student.getAddress();
            address.setCity(studentDto.getCity());
            address.setDistrict(studentDto.getDistrict());
            address.setStreet(studentDto.getStreet());
            addressRepository.save(address);

            studentRepository.save(student);

            return new ApiResponse("edit student succes",true);
        }
        return new ApiResponse("not found student",false);
    }

    @Override
    public ApiResponse deleteStudent(Integer id) {
        try{
            studentRepository.deleteById(id);
            return new ApiResponse("delete student",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }

    }

    @Override
    public Student getByStudentName(String fullName) {
        Optional<Student> student = studentRepository.findByFullName(fullName);
        return student.orElseGet(Student::new);

    }

    @Override
    public List<Object> getByStudentSubject(Integer id) {
        return studentRepository.studentsSubjects(id);
    }

    @Override
    public Integer CountStudentFacultyId(Integer facultyId) {
        return studentRepository.countAllByGroup_FacultyId(facultyId);
    }
}
