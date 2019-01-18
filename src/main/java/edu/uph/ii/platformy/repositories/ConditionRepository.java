package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Condition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConditionRepository extends JpaRepository< Condition, Long >
{
    @Query ( "SELECT c FROM Condition c WHERE (" +
            " c.student.id = :id)" +
            " ORDER BY (CASE WHEN c.status = 'AWAITING' THEN '1' ELSE c.status END) ASC," +
            " c.submittingDate DESC, c.statusChangeDate DESC" )
    Page< Condition > findAllByStudent ( @Param ( "id" ) Long id, Pageable pageable );

    @Query ( "SELECT c FROM Condition c WHERE (" +
            " c.student.id = :id)" +
            " AND (c.status = 'ACCEPTED' OR c.status = 'AWAITING')" )
    List< Condition > findAllByStudent ( @Param ( "id" ) Long id );

    @Query ( "SELECT c FROM Condition c WHERE" +
            " c.subject.instructor.id = :id" +
            " ORDER BY (CASE WHEN c.status = 'AWAITING' THEN '1' ELSE c.status END) ASC," +
            " c.submittingDate DESC, c.statusChangeDate DESC" )
    Page< Condition > findAllByInstructor ( @Param ( "id" ) Long id, Pageable pageable );

    @Query ( "SELECT s FROM Scholarship s WHERE" +
            " s.student.id = :id" +
            " AND s.scholarshipType = :scholarshipType" +
            " AND s.status <> 'REJECTED'" )
    List< Condition > canStudentGetCondition ( Long id );
}
