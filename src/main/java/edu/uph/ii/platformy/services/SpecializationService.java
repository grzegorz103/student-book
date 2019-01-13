package edu.uph.ii.platformy.services;

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
}