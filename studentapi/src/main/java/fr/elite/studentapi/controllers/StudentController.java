package fr.elite.studentapi.controllers;

import fr.elite.studentapi.entities.Student;
import fr.elite.studentapi.repositories.StudentRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Récupérer tous les étudiants
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    // Ajouter un nouvel étudiant
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setStudentNumber(updatedStudent.getStudentNumber());
                    student.setAcademicYearRegistered(updatedStudent.isAcademicYearRegistered());
                    student.setCoursesId(updatedStudent.getCoursesId());
                    student.setAcademicYearId(updatedStudent.getAcademicYearId());
                    student.setSurname(updatedStudent.getSurname());
                    student.setFirstname(updatedStudent.getFirstname());
                    student.setAdress(updatedStudent.getAdress());
                    student.setPw(updatedStudent.getPw());
                    student.setDw(updatedStudent.getDw());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
