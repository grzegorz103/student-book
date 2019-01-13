package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long>
{
}
