package az.pashabank.msstudent.controller;

import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.model.StudentModel;
import az.pashabank.msstudent.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    private static final Logger LOGGER= LoggerFactory.getLogger(StudentController.class);

    // ok
    @GetMapping(value = "/findAllStudents")
    public List<StudentDTO> findAllStudents(HttpServletRequest httpServletRequest) {
        LOGGER.info("findAllStudents "+ httpServletRequest.getRemoteAddr());
        List<StudentDTO> students = studentService.findAllStudents();
        return students;
    }

    // ok
    @GetMapping(value = "/findAllStudentById/{studentId}")
    public StudentDTO findAllStudentById(@PathVariable Long studentId, HttpServletRequest httpServletRequest) {
        LOGGER.info("findAllStudentById "+ httpServletRequest.getRemoteAddr());
        return studentService.findStudentById(studentId);
    }

    // ok
    @PostMapping(value = "/addStudent")
    public ResponseEntity addStudent(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "lastname", required = false) String lastname,
                                     @RequestParam(value = "college_id", required = false) Integer colegeId,
                                     HttpServletRequest httpServletRequest) {
        LOGGER.info("addStudent "+ httpServletRequest.getRemoteAddr());
        StudentModel studentModel = new StudentModel();
        studentModel.setId(id);
        studentModel.setName(name);
        studentModel.setLastname(lastname);
        studentModel.setCollege(colegeId);
        studentService.addStudent(studentModel);
        if (studentModel.getId() != null && studentModel.getName() != null && studentModel.getLastname() != null ) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // ok
    @PostMapping(value = "/updateStudent")
    public ResponseEntity updateStudent(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "lastname", required = false) String lastname,
                                        @RequestParam(value = "college_id", required = false) Integer college_id,
                                        HttpServletRequest httpServletRequest) {
        LOGGER.info("updateStudent "+ httpServletRequest.getRemoteAddr());
        StudentModel studentModel = new StudentModel();
        studentModel.setId(id);
        studentModel.setName(name);
        studentModel.setLastname(lastname);
        studentModel.setCollege(college_id);
        studentService.updateStudent(studentModel);
        if (studentModel.getId() != null && studentModel.getName() != null && studentModel.getLastname() != null ) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // ok
    @PostMapping(value = "/deleteStudent/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId,
                                        HttpServletRequest httpServletRequest) {
        LOGGER.info("deleteStudent "+ httpServletRequest.getRemoteAddr());
        studentService.deleteStudentById(studentId);
        if (studentId != null ) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    // ok
    @GetMapping(value = "findStudentsByCollegeLocation/{city}")
    public List<StudentDTO> findStudentsByCollegeLocation(@PathVariable String city,
                                                          HttpServletRequest httpServletRequest) {
        LOGGER.info("findStudentsByCollegeLocation "+ httpServletRequest.getRemoteAddr());

        return studentService.findStudentsByCollegeLocation(city);
    }

}

