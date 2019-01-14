package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long>
{
        Person findByName ( String name );

}
