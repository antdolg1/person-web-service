package lv.ergo.ergowebservice.testutils;

import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.model.Gender;
import lv.ergo.ergowebservice.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonGenerator {

    public static List<Person> createPersonList() {
        List<Person> persons = new ArrayList<>();
        persons.add(createPerson());
        return persons;
    }

    public static List<PersonDTO> createPersonDTOList() {
        List<PersonDTO> personsDTO = new ArrayList<>();
        personsDTO.add(createPersonDTO());
        return personsDTO;
    }

    public static PersonDTO createPersonDTO() {
        return PersonDTO.builder()
                .id(1L)
                .fullName("Test Person")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .email("test@mail.com")
                .phoneNumber("1234567890")
                .build();
    }

    public static Person createPerson() {
        return Person.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Person")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.now())
                .email("test@mail.com")
                .phoneNumber("1234567890")
                .build();
    }

    public static Person createUpdatedPerson() {
        return Person.builder()
                .id(1L)
                .firstName("Updated")
                .lastName("Person")
                .gender(Gender.FEMALE)
                .dateOfBirth(LocalDate.now())
                .email("updated@mail.com")
                .phoneNumber("0987654321")
                .build();
    }
}
