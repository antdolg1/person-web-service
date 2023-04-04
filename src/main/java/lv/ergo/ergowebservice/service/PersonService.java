package lv.ergo.ergowebservice.service;

import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.exception.PersonLookupException;
import lv.ergo.ergowebservice.mappers.PersonMapper;
import lv.ergo.ergowebservice.model.Person;
import lv.ergo.ergowebservice.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDTO> findByFirstName(final String firstName) {
        List<Person> persons = personRepository.findAllByFirstName(firstName);
        if (persons.isEmpty()) {
            throw new PersonLookupException(String.format("Person with name %s not found", firstName));
        }
        return personMapper.personsToPersonDTO(persons);
    }

    public List<PersonDTO> findByDateOfBirth(final LocalDate dateOfBirth) {
        List<Person> persons = personRepository.findByDateOfBirth(dateOfBirth);
        if (persons.isEmpty()) {
            throw new PersonLookupException(String.format("Person with birth date %s not found", dateOfBirth));
        }
        return personMapper.personsToPersonDTO(persons);
    }

    public List<PersonDTO> findAll() {
        return personMapper.personsToPersonDTO(personRepository.findAll());
    }

    public Person save(final Person person) {
        log.info("Saving person: {}", person);
        return personRepository.save(person);
    }

    public Person update(final Long id, final Person updatedPerson) {
        Person person = personRepository.findById(id).orElseThrow(PersonLookupException.byId(id));

        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setGender(updatedPerson.getGender());
        person.setDateOfBirth(updatedPerson.getDateOfBirth());
        person.setPhoneNumber(updatedPerson.getPhoneNumber());
        person.setEmail(updatedPerson.getEmail());

        return personRepository.save(person);
    }

    public void delete(final Long id) {
        Person person = personRepository.findById(id).orElseThrow(PersonLookupException.byId(id));
        personRepository.delete(person);
        log.info("Person with ID {} was successfully deleted", id);
    }


    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
}