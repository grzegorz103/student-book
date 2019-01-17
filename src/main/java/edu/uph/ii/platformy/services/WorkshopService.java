package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Unit;
import edu.uph.ii.platformy.models.Workshop;

import java.util.List;
import java.util.Set;

public interface WorkshopService
{
        List<Workshop> findWorkshopsByInstructor(Instructor instructor);

        List<Workshop> findAll();

        List<Workshop> findWorkshopList();

        Student findActiveStudent();

        Instructor findActiveInstructor();

        void deleteStudentFromWorkshop(Student student, Workshop workshop);

        void addStudentToWorkshop(Student student, Workshop workshop);

        void addWorkshop(Workshop workshop, Instructor instructor);

        void deleteWorkshop(Workshop workshop);

        void addUnit(Unit unit, Workshop workshop);
}
