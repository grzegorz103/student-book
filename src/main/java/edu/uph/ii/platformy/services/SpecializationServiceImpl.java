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

        private final AccountRepository accountRepository;

        @Autowired
        public SpecializationServiceImpl ( SpecializationRepository specializationRepository, StudentRepository studentRepository, AccountRepository accountRepository )
        {
                this.specializationRepository = specializationRepository;
                this.studentRepository = studentRepository;
                this.accountRepository = accountRepository;
        }


        @Override
        public void addStudentSpecializaion ( Specialization specialization )
        {
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Student currentStudent = ( Student ) u.getPerson();
                currentStudent.setSpecialization( specialization );

                studentRepository.save( currentStudent );
        }

        @Override
        public List<Specialization> getAllSpecializations ()
        {
                return specializationRepository.findAll();
        }
}
