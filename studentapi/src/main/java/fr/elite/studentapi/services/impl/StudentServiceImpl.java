package fr.elite.studentapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.elite.studentapi.entities.Student;
import fr.elite.studentapi.repositories.StudentRepository;
import fr.elite.studentapi.services.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAcademicYear(Long academicYearId) {
        return studentRepository.findByAcademicYearId(academicYearId);
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        if(student.getCoursesId() == null) student.setCoursesId(new ArrayList<>());
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        if(getStudentById(id).isPresent()) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        } else {
            return null;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> createAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }

}
