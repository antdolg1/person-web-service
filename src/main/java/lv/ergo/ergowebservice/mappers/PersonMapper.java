package lv.ergo.ergowebservice.mappers;

import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "fullName", expression = "java(person.getFirstName() + \" \" + person.getLastName())")
    PersonDTO personToPersonDTO(Person person);

    List<PersonDTO> personsToPersonDTO(List<Person> persons);

}
