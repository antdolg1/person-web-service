package lv.ergo.ergowebservice.dto;

import lombok.Builder;
import lombok.Data;
import lv.ergo.ergowebservice.model.Gender;

import java.time.LocalDate;

@Builder
@Data
public class PersonDTO {
    private Long id;
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
}
