package lv.ergo.ergowebservice.repository;

import lv.ergo.ergowebservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByFirstName(String firstName);

    List<Person> findByDateOfBirth(LocalDate dateOfBirth);

}
