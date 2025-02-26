package fr.elite.studentapi.repositories;

import fr.elite.studentapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public class StudentRepository extends JpaRepository<Student, Long> {
    
}
