package az.pashabank.msstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String lastname;
    private Integer college;

}
