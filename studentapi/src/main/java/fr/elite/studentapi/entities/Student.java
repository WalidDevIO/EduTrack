package fr.elite.studentapi.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
public class Student {
	//Id
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le numéro étudiant est obligatoire")
    private String studentNumber;

    private boolean academicYearRegistered;

    private List<Long> coursesId;

    private Long academicYearId;

    @NotBlank(message = "Le nom de famille est obligatoire")
    private String surname;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstname;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adress;

    @Min(value = 1, message = "Le groupe de TP doit être au moins 1")
    private Integer pw;

    @Min(value = 1, message = "Le groupe de TD doit être au moins 1")
    private Integer dw;

}
