package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemestralGradesRepository extends JpaRepository< SemestralGrade, Long >
{
    List< SemestralGrade > findAllBySubject ( Subject subject );

    @Query ( "SELECT s FROM SemestralGrade s WHERE (s.student.id = :id AND s.subject.semester = :semester)" )
    List< SemestralGrade > findSemestralGradesByStudentAndSemester ( @Param ( "id" ) Long id, @Param ( "semester" ) Long semester );
}

