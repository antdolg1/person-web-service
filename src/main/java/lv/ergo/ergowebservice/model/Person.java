package lv.ergo.ergowebservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "firstName is mandatory")
    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    @NotNull(message = "lastName cannot be null")
    private String lastName;

    private Gender gender;

    @Past
    private LocalDate dateOfBirth;

    @NotBlank(message = "phoneNumber is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "phoneNumber should contain 10 digits")
    private String phoneNumber;

    @Email
    private String email;
}
