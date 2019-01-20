package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.models.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long>
{
        List<Opinion> findAllByInstructor ( Instructor instructor );

        List<Opinion> findAllByStatus(Statuses status);
}
