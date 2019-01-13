package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Utils;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.SpecializationRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import edu.uph.ii.platformy.repositories.UtilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("specializationService")
public class SpecializationServiceImpl implements SpecializationService
{
        private final SpecializationRepository specializationRepository;

        private final StudentRepository studentRepository;

        private final UtilsRepository utilsRepository;

        private final AccountRepository accountRepository;

        @Autowired
        public SpecializationServiceImpl ( SpecializationRepository specializationRepository,
                                           StudentRepository studentRepository,
                                           AccountRepository accountRepository,
                                           UtilsRepository utilsRepository )
        {
                this.specializationRepository = specializationRepository;
                this.studentRepository = studentRepository;
                this.utilsRepository = utilsRepository;
                this.accountRepository = accountRepository;
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


        @Override
        public boolean isEnabled ()
        {
                return utilsRepository.findById( 1L ).get().getSpecialization_enabled();
        }


        @Override
        public boolean isUserSpecialization ()
        {
                User user = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Student currentStudent = ( Student ) u.getPerson();
                return currentStudent.getSpecChosen();

        }


        @Override
        public void editStatus ()
        {
                Utils status = utilsRepository.getOne( 1L );
                if ( isEnabled() )
                {
                        status.setSpecialization_enabled( Boolean.FALSE );
                        confirmSpecialization();
                } else
                        status.setSpecialization_enabled( Boolean.TRUE );
                utilsRepository.save( status );
        }


        private void confirmSpecialization ()
        {
                List<Student> students = studentRepository.findAll();

                for ( Student x : students )
                {
                        if ( !x.getSpecChosen() )
                        {
                                if ( x.getSpecialization() == null )
                                        x.setSpecialization( getSpecialization() );
                                x.setSpecChosen( true );
                        }
                        studentRepository.save( x );
                }
        }


        private Specialization getSpecialization ()
        {
                List<Specialization> list = specializationRepository.findAll();
                long number = Long.MAX_VALUE;
                for ( Specialization x : list )
                {
                        int studNumber = studentRepository.findAllBySpecialization( x ).size();
                        if ( number > studNumber )
                                number = studNumber;
                }
                for ( Specialization x : list )
                        if ( number == studentRepository.findAllBySpecialization( x ).size() )
                                return x;
                return specializationRepository.getOne( 1L );
        }
}
