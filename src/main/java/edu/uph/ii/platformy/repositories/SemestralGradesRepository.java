package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemestralGradesRepository extends JpaRepository<SemestralGrade, Long>
{
        List<SemestralGrade> findAllBySubject ( Subject subject );
}

