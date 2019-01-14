package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.uph.ii.platformy.repositories.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer
{

    private final InstructorRepository instructorRepository;

    private final DeanRepository deanRepository;

    private final StudentRepository studentRepository;

    private final RoleRepository roleRepository;

    private final CourseRepository courseRepository;

    private final SpecializationRepository specializationRepository;

    private final AccountRepository accountRepository;

    private final SubjectRepository subjectRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RepositoriesInitializer ( InstructorRepository instructorRepository, DeanRepository deanRepository, StudentRepository studentRepository, RoleRepository roleRepository, CourseRepository courseRepository, SpecializationRepository specializationRepository, AccountRepository accountRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder )
    {
        this.instructorRepository = instructorRepository;
        this.deanRepository = deanRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.specializationRepository = specializationRepository;
        this.accountRepository = accountRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    InitializingBean init ()
    {
        return () -> {
            if ( this.specializationRepository.findAll ()
                    .isEmpty () )
            {
                this.specializationRepository.save ( new Specialization ( 1L, "bazy danych", 30L ) );
                this.specializationRepository.save ( new Specialization ( 2L, "programowanie", 20L ) );
            }
            if ( this.courseRepository.findAll ()
                    .isEmpty () )
            {
                this.courseRepository.save ( new Course ( 1L, "informatyka" ) );
                this.courseRepository.save ( new Course ( 2L, "matematyka" ) );
                this.courseRepository.save ( new Course ( 3L, "chemia" ) );
                this.courseRepository.save ( new Course ( 4L, "fizyka" ) );
            }

            if ( this.roleRepository.findAll ()
                    .isEmpty () )
            {
                Role roleStudent = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_STUDENT ) );
                Role roleInstructor = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_INSTRUCTOR ) );
                Role roleDean = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_DEAN ) );

                User dean = new User ( 1L, "dean@dean.pl" );
                dean.setRoles ( new HashSet<> ( Arrays.asList ( roleDean ) ) );
                dean.setPassword ( this.passwordEncoder.encode ( "dean" ) );
                dean.setPasswordConfirm ( dean.getPassword () );
                dean.setPerson ( this.deanRepository.save ( new Dean ( 1L, "Dean", "Dean", true, 12345678901L ) ) );

                User instructor = new User ( 2L, "instructor@instructor.pl" );
                instructor.setRoles ( new HashSet<> ( Arrays.asList ( roleInstructor ) ) );
                instructor.setPassword ( this.passwordEncoder.encode ( "instructor" ) );
                instructor.setPasswordConfirm ( instructor.getPassword () );
                Instructor instructorPerson = this.instructorRepository.save ( new Instructor ( 2L, "Instructor", "Instructor", true, 23456789012L ) );
                instructor.setPerson ( instructorPerson );

                User student = new User ( 3L, "student@student.pl" );
                student.setRoles ( new HashSet<> ( Arrays.asList ( roleStudent ) ) );
                student.setPassword ( this.passwordEncoder.encode ( "student" ) );
                student.setPasswordConfirm ( student.getPassword () );
                Student studentPerson = new Student ( 3L, "Student", "Student", true, 34567890123L );
                studentPerson.setAge ( 20 );
                studentPerson.setCourse ( this.courseRepository.findById ( 1L )
                        .orElse ( null ) );
                studentPerson.setSpecialization ( this.specializationRepository.findById ( 1L )
                        .orElse ( null ) );
                studentPerson.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson.setSemester ( 2L );
                studentPerson.setBankAccountNumber ( "12345678901234567890123456" );
                student.setPerson ( this.studentRepository.save ( studentPerson ) );

                this.accountRepository.save ( dean );
                this.accountRepository.save ( instructor );
                this.accountRepository.save ( student );

                if ( this.subjectRepository.findAll ()
                        .isEmpty () )
                {
                    this.subjectRepository.save ( new Subject ( 1L, "inżynieria oprogramowania", 2L, this.courseRepository.findById ( 1L )
                            .orElse ( null ), instructorPerson ) );
                }

            }
        };
    }

}