package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Error;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Long>
{
}
