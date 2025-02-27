package fr.elite.studentapi.controllers;

import fr.elite.studentapi.entities.Student;
import fr.elite.studentapi.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<?>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students); // 200 OK

    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok) // 200 OK si trouvé
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found si non trouvé
    }

    // Ajouter un nouvel étudiant
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try{
            Student createdStudent = studentRepository.save(student);
            return ResponseEntity.status(201).body(createdStudent); // 201 Student créé
        }catch (Exception e){
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    //student.setStudentNumber(updatedStudent.getStudentNumber());
                    student.setAcademicYearRegistered(updatedStudent.isAcademicYearRegistered());
                    student.setCoursesId(updatedStudent.getCoursesId());
                    student.setAcademicYearId(updatedStudent.getAcademicYearId());
                    student.setSurname(updatedStudent.getSurname());
                    student.setFirstname(updatedStudent.getFirstname());
                    student.setAdress(updatedStudent.getAdress());
                    student.setPw(updatedStudent.getPw());
                    student.setDw(updatedStudent.getDw());
                    studentRepository.save(student);
                    return ResponseEntity.ok(student); // 200 OK après mise à jour
                })
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found si non trouvé
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content après suppression
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si non trouvé
        }
    }

    @PostMapping('/import-students')
    public ResponseEntity<?> importStudent (@Valid @RequestBody List<Student> students){
        if (students == null || students.isEmpty()) {
            return ResponseEntity.badRequest().body("La liste d'étudiants est vide."); // 400 Bad Request
        }
    
        try {
            List<Student> createdStudents = studentRepository.saveAll(students);
            return ResponseEntity.status(201).body(createdStudents); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'importation des étudiants : " + e.getMessage()); // 500 avec message
        }
    }
}
