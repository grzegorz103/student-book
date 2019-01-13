package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>
{
        List<Student> findAllBySpecialization ( Specialization specialization );

        List<Student> findAllByCourse(Course course);
}
