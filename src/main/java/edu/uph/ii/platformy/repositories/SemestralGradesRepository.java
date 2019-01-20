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

    @Query ( "SELECT s FROM SemestralGrade s WHERE (" +
            " s.student.id = :id" +
            " AND s.subject.semester <= :semester" +
            " AND (s.totalGrade = null OR s.totalGrade < 3)" +
            " AND s.subject.course.id = :course)" )
    List< SemestralGrade > findSemestralGradesByStudentForCondition ( @Param ( "id" ) Long id, @Param ( "semester" ) Long semester, @Param ( "course" ) Long course );

    @Query ( "SELECT s FROM SemestralGrade s WHERE (" +
            " s.student.id = :id)" +
            " ORDER BY s.subject.semester, s.subject.id" )
    List< SemestralGrade > findAllByStudentOrdered ( @Param ( "id" ) Long id );
}

