package az.pashabank.msstudent.service;

import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.model.StudentModel;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(Long studentId);
    void addStudent(StudentModel student);
    void updateStudent(StudentModel student);
    void deleteStudentById(Long studentId);
    List<StudentDTO> findStudentsByCollegeLocation(String city);

}
