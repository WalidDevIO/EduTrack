package fr.elite.studentapi.repositories;

import fr.elite.studentapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
