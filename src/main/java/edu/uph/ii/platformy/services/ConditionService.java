package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Condition;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConditionService
{

    Page< Condition > findConditions ( Pageable pageable );

    boolean canStudentGetCondition ();

    List<Subject> getStudentSubjectsForCondition ();

    boolean isOpen ();

    boolean conditionBelongsToLoggedStudent ( Condition condition );

    void save ( Condition condition );

    void acceptCondition ( Condition condition );

    void rejectCondition ( Condition condition );

    void openOrClose ();
}
