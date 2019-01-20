package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Course;

import java.util.List;

public interface CourseService
{
    List< Course > findAvailableCourses ();

    Course getActualStudentCourse ();

    List< Course> getAllCourses ();
}
