package lv.ergo.ergowebservice.mappers;

import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.model.Person;
import lv.ergo.ergowebservice.testutils.PersonGenerator;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonMapperTest {

    private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Test
    public void testPersonToPersonDTO() {
        Person person = PersonGenerator.createPerson();
        PersonDTO expectedPersonDTO = PersonGenerator.createPersonDTO();
        PersonDTO result = personMapper.personToPersonDTO(person);

        assertEquals(expectedPersonDTO, result);
    }

    @Test
    public void testPersonsToPersonDTO() {
        List<Person> persons = PersonGenerator.createPersonList();
        List<PersonDTO> expectedPersonsDTO = PersonGenerator.createPersonDTOList();
        List<PersonDTO> result = personMapper.personsToPersonDTO(persons);

        assertEquals(expectedPersonsDTO, result);
    }
}
