package fr.elite.studentapi.controllers;

import fr.elite.studentapi.entities.Student;
import fr.elite.studentapi.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Récupérer tous les étudiants
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required=false) Long formation) {
        List<Student> students;
        if(formation != null) {
            students = studentService.getStudentsByAcademicYear(formation);
        } else {
            students = studentService.getAllStudents();
        }
        return ResponseEntity.ok(students); // 200 OK
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok) // 200 OK si trouvé
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found si non trouvé
    }

    // Ajouter un nouvel étudiant
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try{
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.status(201).body(createdStudent); // 201 Student créé
        }catch (Exception e){
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody Student updatedStudent) {
        Student updated = studentService.updateStudent(id, updatedStudent);
        if(updated == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build(); // 204 No Content après suppression
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si non trouvé
        }
    }

    @PostMapping("/import-students")
    public ResponseEntity<?> importStudent (@Valid @RequestBody List<Student> students){
        if (students == null || students.isEmpty()) {
            return ResponseEntity.badRequest().body("La liste d'étudiants est vide."); // 400 Bad Request
        }
    
        try {
            List<Student> createdStudents = studentService.createAll(students);
            return ResponseEntity.status(201).body(createdStudents); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'importation des étudiants : " + e.getMessage()); // 500 avec message
        }
    }
}
