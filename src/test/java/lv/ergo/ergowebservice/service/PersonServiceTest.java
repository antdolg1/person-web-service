package lv.ergo.ergowebservice.service;

import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.exception.PersonLookupException;
import lv.ergo.ergowebservice.mappers.PersonMapper;
import lv.ergo.ergowebservice.model.Person;
import lv.ergo.ergowebservice.repository.PersonRepository;
import lv.ergo.ergowebservice.testutils.PersonGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testFindByFirstName_ShouldReturnMatchingRecords() {
        List<Person> persons = PersonGenerator.createPersonList();
        when(personRepository.findAllByFirstName(any())).thenReturn(persons);

        List<PersonDTO> expectedPersons = PersonGenerator.createPersonDTOList();
        when(personMapper.personsToPersonDTO(persons)).thenReturn(expectedPersons);

        List<PersonDTO> result = personService.findByFirstName("Test");

        assertEquals(expectedPersons, result);
    }

    @Test(expected = PersonLookupException.class)
    public void testFindByFirstName_ShouldThrowPersonLookupException() {
        List<Person> persons = new ArrayList<>();
        when(personRepository.findAllByFirstName(any())).thenReturn(persons);

        personService.findByFirstName("Fail");
    }

    @Test
    public void testFindByDateOfBirth__ShouldReturnMatchingRecords() {
        List<Person> persons = PersonGenerator.createPersonList();
        when(personRepository.findByDateOfBirth(any())).thenReturn(persons);

        List<PersonDTO> expectedPersons = PersonGenerator.createPersonDTOList();
        when(personMapper.personsToPersonDTO(persons)).thenReturn(expectedPersons);

        List<PersonDTO> result = personService.findByDateOfBirth(LocalDate.of(2000, 1, 1));

        assertEquals(expectedPersons, result);
    }

    @Test(expected = PersonLookupException.class)
    public void testFindByDateOfBirthNoResult_ShouldThrowPersonLookupException() {
        List<Person> persons = new ArrayList<>();
        when(personRepository.findByDateOfBirth(any())).thenReturn(persons);

        personService.findByDateOfBirth(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testFindAll_ShouldReturnMatchingRecords() {
        List<Person> persons = PersonGenerator.createPersonList();
        when(personRepository.findAll()).thenReturn(persons);

        List<PersonDTO> expectedPersons = PersonGenerator.createPersonDTOList();
        when(personMapper.personsToPersonDTO(any())).thenReturn(expectedPersons);

        List<PersonDTO> result = personService.findAll();

        assertEquals(expectedPersons, result);
    }


    @Test
    public void testSave_ShouldSavePerson() {
        Person person = PersonGenerator.createPerson();
        when(personRepository.save(any())).thenReturn(person);

        Person result = personService.save(person);

        verify(personRepository, times(1)).save(person);
        assertEquals(person, result);
    }

    @Test
    public void testUpdate_ShouldUpdatePerson() {
        Person person = PersonGenerator.createPerson();
        when(personRepository.save(any())).thenReturn(person);
        when(personRepository.findById(any())).thenReturn(Optional.of(person));

        person = personService.save(person);

        Person updatedPerson = PersonGenerator.createUpdatedPerson();
        Person result = personService.update(person.getId(), updatedPerson);

        assertEquals(updatedPerson.getFirstName(), result.getFirstName());
        assertEquals(updatedPerson.getLastName(), result.getLastName());
        assertEquals(updatedPerson.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(updatedPerson.getEmail(), result.getEmail());
        assertEquals(updatedPerson.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(updatedPerson.getGender(), result.getGender());
    }

    @Test(expected = PersonLookupException.class)
    public void testUpdateWithInvalidId_ShouldThrowPersonLookupException() {
        Person person = PersonGenerator.createPerson();
        personService.update(123L, person);
    }

    @Test
    public void testDelete_ShouldDeleteExistingPerson() {
        Person person = PersonGenerator.createPerson();
        when(personRepository.findById(any())).thenReturn(Optional.of(person));
        personService.delete(person.getId());

        verify(personRepository, times(1)).findById(any());
        verify(personRepository, times(1)).delete(person);
    }

    @Test(expected = PersonLookupException.class)
    public void testDeleteWithInvalidId_ShouldThrowPersonLookupException() {
        personService.delete(123L);
    }
}
