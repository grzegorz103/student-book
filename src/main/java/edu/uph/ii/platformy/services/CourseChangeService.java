package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.CourseChange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseChangeService
{
    Page< CourseChange > findCourseChange ( Pageable pageable );

    boolean hasStudentAwaitingCourseChange ();

    boolean courseChangeBelongsToLoggedStudent ( CourseChange courseChange );

    void save ( CourseChange courseChange );

    void acceptCourseChange ( CourseChange courseChange );

    void rejectCourseChange ( CourseChange courseChange );
}
