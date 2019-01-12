package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;

import java.util.List;

public interface InstructorService
{
        List<Opinion> getOpinions ( Instructor instructor );

        void addOpinion(Opinion opinion, Instructor instructor);
}
