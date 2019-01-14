package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Information;
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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service ("specializationService")
public class SpecializationServiceImpl implements SpecializationService
{
        private final SpecializationRepository specializationRepository;

        private final StudentRepository studentRepository;

        private final UtilsRepository utilsRepository;

        private final AccountRepository accountRepository;

        @Autowired
        private InformationService informationService;

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
                Utils status =utilsRepository.getOne( 1L );
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
                List<Student> students = studentRepository.findAll()
                        .stream()
                        .filter( e -> !e.getSpecChosen() )
                        .collect( Collectors.toList() );

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
                createInfo( students );
        }

        private void createInfo ( List<Student> students )
        {
                Information information = new Information();
                information.setDate( new Date() );
                final String[] a = {"Wyniki wyborów specjalizacji: \r\n"};
                students.forEach( e -> a[0] += e.getPesel() + " " + e.getSpecialization().getName() + "\r\n" );
                information.setTitle( "Wyniki wyborów specjalizacji" );
                information.setText( a[0] );
                information.setAuthor( "Dziekan" );
                informationService.add( information );
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
