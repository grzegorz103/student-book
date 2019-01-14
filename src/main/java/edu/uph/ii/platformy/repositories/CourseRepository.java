package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository< Course, Long >
{
}
