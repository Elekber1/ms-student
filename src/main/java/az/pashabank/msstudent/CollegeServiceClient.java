package az.pashabank.msstudent;

import az.pashabank.msstudent.dto.CollegeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(url = "localhost:8088", value = "College-Client")
public interface CollegeServiceClient {

    @GetMapping("/findAllCollege")
    public List<CollegeDto> findAllCollege();

    @GetMapping(value = "/findCollegeById/{collegeId}")
    CollegeDto findCollegeById(@PathVariable Integer collegeId);
}
