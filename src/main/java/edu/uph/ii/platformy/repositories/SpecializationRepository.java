package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>
{
       // @Query("select s from Specialization s " +
       //         "")
      //  Specialization getSpec
}
