package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Scholarship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScholarshipRepository extends JpaRepository< Scholarship, Long >
{
    @Query ( "SELECT s FROM Scholarship s WHERE (" +
            " s.student.id = :id)" )
    Page< Scholarship > findAllByStudent ( @Param ( "id" ) Long id, Pageable pageable );
}
