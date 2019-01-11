package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Long>
{
        Opinion findAllByInstructor ( Instructor instructor );
}
