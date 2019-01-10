package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long>
{

}
