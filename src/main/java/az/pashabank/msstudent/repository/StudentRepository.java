package az.pashabank.msstudent.repository;

import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.model.StudentModel;

import java.util.List;

public interface StudentRepository {

    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(Long studentId);
    void addStudent(StudentModel student);
    void updateStudent(StudentModel student);
    void deleteStudentById(Long studentId);
}
