package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Scholarship;
import edu.uph.ii.platformy.models.ScholarshipTypes;
import edu.uph.ii.platformy.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScholarshipService
{
    Page< Scholarship > findScholarships ( Pageable pageable );

    void acceptScholarship ( Scholarship scholarship );

    void rejectScholarship ( Scholarship scholarship );

    boolean scholarshipBelongsToLoggedStudent ( Scholarship scholarship );

    boolean hasStudentAwaitingScholarship ( ScholarshipTypes scholarshipTypes );

    void save ( Scholarship scholarship );

    Double getPreviousSemesterStudentsMarks ( Student student );

    void openOrClose ();

    boolean isOpen ();
}
