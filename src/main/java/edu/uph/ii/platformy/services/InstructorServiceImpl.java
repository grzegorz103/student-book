package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("instructorService")
public class InstructorServiceImpl implements InstructorService
{
        @Autowired
        private InstructorRepository instructorRepository;

        @Autowired
        private OpinionRepository opinionRepository;

        @Override
        public List<Opinion> getOpinions ( Instructor instructor )
        {
                return opinionRepository.findAllByInstructor( instructor );
        }

        @Override
        public void addOpinion(Opinion opinion, Instructor instructor) {

                opinion.setStatus(Statuses.AWAITING);
                opinion.setInstructor(instructor);

                opinionRepository.save(opinion);
        }
}
