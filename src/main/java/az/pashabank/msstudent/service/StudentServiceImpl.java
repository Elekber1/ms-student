package az.pashabank.msstudent.service;

import az.pashabank.msstudent.CollegeServiceClient;
import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.model.StudentModel;
import az.pashabank.msstudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private CollegeServiceClient collegeServiceClient;

    @Override
    public List<StudentDTO> findAllStudents() {

        return studentRepository.findAllStudents();
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public void addStudent(StudentModel student) {
    studentRepository.addStudent(student);
    }

    @Override
    public void updateStudent(StudentModel student) {
    studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteStudentById(studentId);
    }

    @Override
    public List<StudentDTO> findStudentsByCollegeLocation(String city) {


        return studentRepository.findAllStudents()
                .stream()
                .filter(studentDTO -> collegeServiceClient
                        .findCollegeById(studentDTO.getCollege()).getCity().equals(city)).collect(Collectors.toList());
    }
}
