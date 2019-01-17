package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.CourseChange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseChangeRepository extends JpaRepository< CourseChange, Long >
{
    @Query ( "SELECT c FROM CourseChange c WHERE (" +
            " c.student.id = :id)" +
            " ORDER BY (CASE WHEN c.status = 'AWAITING' THEN '1' ELSE c.status END) ASC, c.submittingDate DESC, c.statusChangeDate DESC" )
    Page< CourseChange > findAllByStudent ( @Param ( "id" ) Long id, Pageable pageable );

    @Query ( "SELECT c FROM CourseChange c" +
            " ORDER BY (CASE WHEN c.status = 'AWAITING' THEN '1' ELSE c.status END) ASC, c.submittingDate DESC, c.statusChangeDate DESC" )
    Page< CourseChange > findAllCourseChange ( Pageable pageable );

    @Query ( "SELECT c FROM CourseChange c WHERE" +
            " c.student.id = :id AND c.status = 'AWAITING'" )
    List< CourseChange > findAwaitingsByStudent ( @Param ( "id" ) Long id );
}
