package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository< Scholarship, Long >
{
}
