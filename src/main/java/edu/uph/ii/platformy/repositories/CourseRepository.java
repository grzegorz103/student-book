package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository< Course, Long >
{
    @Query ( "SELECT c FROM Course c WHERE (" +
            " c.id <> :id) ORDER BY c.name ASC" )
    List< Course > findAvailableCourses ( @Param ( "id" ) Long id );
}
