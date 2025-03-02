package fr.elite.studentapi.services;

import fr.elite.studentapi.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student updatedStudent);
    void deleteStudent(Long id);
    List<Student> getStudentsByAcademicYear(Long academicYearId);
    List<Student> createAll(List<Student> students);
}
