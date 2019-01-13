package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Attendance;
import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>
{

    List<Attendance> findAllByLesson(Lesson Lesson);
}
