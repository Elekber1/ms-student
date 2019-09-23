package az.pashabank.msstudent;

import az.pashabank.msstudent.dto.CollegeDto;
import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableFeignClients
public class MsStudentApplication {

    @Autowired
    StudentService studentService;

    @Autowired
    CollegeServiceClient collegeServiceClient;

    @GetMapping("/getColleges")
    public List<CollegeDto> findAllStudents() {
        return collegeServiceClient.findAllCollege();
    }

    @GetMapping("/getCollegeById/{city}")
 public List<StudentDTO> getAllStudentByColegeLocation(@PathVariable String city){
        return studentService.findStudentsByCollegeLocation(city);

    }

    public static void main(String[] args)
    {
        SpringApplication.run(MsStudentApplication.class, args);
    }

}
