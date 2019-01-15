package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Scholarship;
import edu.uph.ii.platformy.models.ScholarshipTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository< Scholarship, Long >
{
    @Query ( "SELECT s FROM Scholarship s WHERE (" +
            " s.student.id = :id)" )
    Page< Scholarship > findAllByStudent ( @Param ( "id" ) Long id, Pageable pageable );

    @Query ( "SELECT s FROM Scholarship s WHERE (" +
            " s.student.id = :id AND s.scholarshipType = :scholarshipType AND s.status != 'REJECTED')" )
    List< Scholarship > findAwaitingsByStudentAndScholarshipType ( @Param ( "id" ) Long id, @Param ( "scholarshipType" ) ScholarshipTypes scholarshipType );
}
