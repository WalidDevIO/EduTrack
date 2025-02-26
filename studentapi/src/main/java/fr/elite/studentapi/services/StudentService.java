package fr.elite.studentapi.services;

import fr.elite.studentapi.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(String id);
    Student createStudent(Student student);
    Student updateStudent(String id, Student updatedStudent);
    void deleteStudent(String id);
}
