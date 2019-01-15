package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Subject;

import java.util.List;

public interface SemestralGradeService
{
        List<SemestralGrade> getSemestralGradesBySubject ( Subject subject );
}