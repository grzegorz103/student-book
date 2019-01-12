package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.SpecializationRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("specializationService")
public class SpecializationServiceImpl implements SpecializationService
{
        private final SpecializationRepository specializationRepository;

        private final StudentRepository studentRepository;


        @Autowired
        public SpecializationServiceImpl ( SpecializationRepository specializationRepository, StudentRepository studentRepository, AccountRepository accountRepository )
        {
                this.specializationRepository = specializationRepository;
                this.studentRepository = studentRepository;
        }


        @Override
        public void addStudentSpecializaion ( Specialization specialization )
        {

        }

        @Override
        public void addStudentSpecializaion ( Student student )
        {
                studentRepository.save( student );
        }

        @Override
        public List<Specialization> getAllSpecializations ()
        {
                return specializationRepository.findAll();
        }
}
