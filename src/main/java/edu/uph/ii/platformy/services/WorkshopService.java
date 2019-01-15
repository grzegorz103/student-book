package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Workshop;

import java.util.List;

public interface WorkshopService
{
        List<Workshop> findWorkshopsByInstructor(Instructor instructor);

        List<Workshop> findAll();

        List<Workshop> findWorkshopList();

        Student findActiveStudent();

        void deleteStudentFromWorkshop(Student student, Workshop workshop);

        void addStudentToWorkshop(Student student, Workshop workshop);
}
