package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.SpecializationRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import edu.uph.ii.platformy.repositories.UtilsRepository;
import edu.uph.ii.platformy.services.declarations.InformationService;
import edu.uph.ii.platformy.services.declarations.SpecializationService;
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
                Utils status = utilsRepository.getOne( 1L );
                if ( isEnabled() )
                {
                        status.setSpecialization_enabled( Boolean.FALSE );
                        confirmSpecialization();
                } else
                        status.setSpecialization_enabled( Boolean.TRUE );
                utilsRepository.save( status );
        }

        @Override
        public List<Specialization> getSpecializationById ( Student student )
        {
                return specializationRepository.findAllByCourse( student.getCourse() )
                        .stream()
                        .filter( e -> e.getLimit() > studentRepository.findAllBySpecialization( e ).stream()
                                .filter( f -> !f.getSpecChosen() ).count() )
                        .collect( Collectors.toList() );
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
                                        x.setSpecialization( getSpecialization( x.getCourse() ) );
                                //   x.setSpecChosen( true );
                                studentRepository.save( x );
                        }
                }
                students.forEach( e -> e.setSpecChosen( true ) );
                studentRepository.saveAll( students );
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


        public Specialization getSpecialization ( Course course )
        {
                List<Specialization> list = specializationRepository.findAll()
                        .stream()
                        .filter( e -> e.getCourse() == course )
                        .filter( e -> e.getLimit() > studentRepository.findAllBySpecialization( e ).stream()
                                .filter( f -> !f.getSpecChosen() ).count() )
                        .collect( Collectors.toList() );

                long number = Long.MAX_VALUE;
                Specialization temp = null;
                for ( Specialization x : list )
                {
                        long studNumber = studentRepository.findAllBySpecialization( x ).stream()
                                .filter( e -> !e.getSpecChosen() ).count();
                        if ( number > studNumber )
                        {
                                number = studNumber;
                                temp = x;
                        }
                }
                return temp;
        }

}
