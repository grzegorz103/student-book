package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>
{
        List<Specialization> findAllByCourse ( Course course );
}
