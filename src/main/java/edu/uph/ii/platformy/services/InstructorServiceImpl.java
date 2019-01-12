package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import edu.uph.ii.platformy.repositories.SubjectRepository;
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

                opinion.setId(null);
                opinion.setStatus(Statuses.AWAITING);
                opinion.setInstructor(instructor);

                opinionRepository.save(opinion);
        }

        @Override
        public List <Instructor> findAll() {
                return instructorRepository.findAll();
        }

        @Override
        public void changeOpinionStatus(Long opn, Long sta) {

                Opinion opinion = opinionRepository.findById(opn).get();
                opinion.setStatus((sta == 1) ? Statuses.ACCEPTED : Statuses.REJECTED);
                opinionRepository.save(opinion);
        }

}
