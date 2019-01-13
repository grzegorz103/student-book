package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Attendance;
import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;

import java.util.List;

public interface SubjectService
{

        List<Subject> findAll();

        List<Lesson> getLessons(Subject subject);

        List<Attendance> getAttendances(Lesson lesson);

        void changeStudentAttendance(Long atd, Long sts);
}
