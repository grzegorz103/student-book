package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>
{

        List<Subject> findAllByCourse(Course course);

        List<Subject> findAllByInstructor(Instructor instructor);
}
