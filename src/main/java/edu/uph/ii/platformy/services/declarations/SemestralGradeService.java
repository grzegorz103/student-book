package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.Grade;
import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Subject;

import java.util.List;

public interface SemestralGradeService
{
    List< SemestralGrade > getSemestralGradesBySubject ( Subject subject );

    List< SemestralGrade > getSemestralGradesByStudentAndSemester ( Student student );

    List< SemestralGrade > getSemestralGradesByStudentForCondition ( Student student );

    void changeGrades ( SemestralGrade grade );

    List< SemestralGrade > getSemestralGradesByStudent ();

    Double getActualAvgGradesForStudent ();
}
