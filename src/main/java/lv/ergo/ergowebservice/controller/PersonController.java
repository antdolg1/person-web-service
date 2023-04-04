package lv.ergo.ergowebservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lv.ergo.ergowebservice.dto.PersonDTO;
import lv.ergo.ergowebservice.model.Person;
import lv.ergo.ergowebservice.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/persons")
@Validated
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestBody @Valid Person person) {
        personService.save(person);
    }

    @GetMapping("/name/{name}")
    public List<PersonDTO> findByName(@PathVariable @NotBlank String name) {
        return personService.findByFirstName(name);
    }

    @GetMapping("/birth-date/{dateOfBirth}")
    public List<PersonDTO> findByDateOfBirth(@PathVariable @NotNull LocalDate dateOfBirth) {
        return personService.findByDateOfBirth(dateOfBirth);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody @Valid Person updatedPerson) {
        updatedPerson.setId(id);
        personService.save(updatedPerson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
