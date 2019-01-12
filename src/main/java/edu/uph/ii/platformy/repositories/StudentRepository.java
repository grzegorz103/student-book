package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>
{
}
