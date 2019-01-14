package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.CourseChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseChangeRepository extends JpaRepository< CourseChange, Long >
{
}
