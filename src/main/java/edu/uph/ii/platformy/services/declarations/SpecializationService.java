package edu.uph.ii.platformy.services.declarations;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;

import java.util.List;

public interface SpecializationService
{
        void addStudentSpecializaion ( Student student );

        List<Specialization> getAllSpecializations ();

        boolean isEnabled ();

        boolean isUserSpecialization ();

        void editStatus ();

        List<Specialization> getSpecializationById ( Student student );

        Specialization getSpecialization ( Course course );
}
