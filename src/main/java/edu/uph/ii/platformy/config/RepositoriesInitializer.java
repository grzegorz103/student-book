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

                        if ( this.courseRepository.findAll()
                                .isEmpty() )
                        {
                                this.courseRepository.save( new Course( 1L, "Informatyka" ) );
                                this.courseRepository.save( new Course( 2L, "Matematyka" ) );
                                this.courseRepository.save( new Course( 3L, "Chemia" ) );
                                this.courseRepository.save( new Course( 4L, "Fizyka" ) );
                        }
                        if ( this.specializationRepository.findAll()
                                .isEmpty() )
                        {
                                this.specializationRepository.save( new Specialization( 1L, "Bazy danych", 30L, courseRepository.findById( 1L ).orElse( null ) ) );
                                this.specializationRepository.save( new Specialization( 2L, "Programowanie", 20L, courseRepository.findById( 1L ).orElse( null ) ) );
                                this.specializationRepository.save( new Specialization( 3L, "Optyka", 20L, courseRepository.findById( 4L ).orElse( null ) ) );
                                this.specializationRepository.save( new Specialization( 4L, "Nanostruktury", 20L, courseRepository.findById( 3L ).orElse( null ) ) );
                        }

                        if ( this.roleRepository.findAll()
                                .isEmpty() )
                        {

                                Role roleStudent = this.roleRepository.save( new Role( Role.UserTypes.ROLE_STUDENT ) );
                                Role roleInstructor = this.roleRepository.save( new Role( Role.UserTypes.ROLE_INSTRUCTOR ) );
                                Role roleDean = this.roleRepository.save( new Role( Role.UserTypes.ROLE_DEAN ) );

                                User dean = new User( 1L, "dean@dean.pl" );
                                dean.setRoles( new HashSet<>( Arrays.asList( roleDean ) ) );
                                dean.setPassword( this.passwordEncoder.encode( "dean" ) );
                                dean.setPasswordConfirm( dean.getPassword() );
                                dean.setPerson( this.deanRepository.save( new Dean( 1L, "Dean", "Dean", true, 12345678901L ) ) );

                                User instructor = new User( 2L, "instructor@instructor.pl" );
                                instructor.setRoles( new HashSet<>( Arrays.asList( roleInstructor ) ) );
                                instructor.setPassword( this.passwordEncoder.encode( "instructor" ) );
                                instructor.setPasswordConfirm( instructor.getPassword() );
                                Instructor instructorPerson = this.instructorRepository.save( new Instructor( 2L, "Instructor", "Instructor", true, 23456789012L ) );
                                instructor.setPerson( instructorPerson );

                                User student = new User( 3L, "student@student.pl" );
                                student.setRoles( new HashSet<>( Arrays.asList( roleStudent ) ) );
                                student.setPassword( this.passwordEncoder.encode( "student" ) );
                                student.setPasswordConfirm( student.getPassword() );
                                Student studentPerson = new Student( 3L, "Student", "Student", true, 34567890123L );
                                studentPerson.setAge( 20 );
                                studentPerson.setCourse( this.courseRepository.findById( 1L )
                                        .orElse( null ) );
                                studentPerson.setSpecialization( this.specializationRepository.findById( 1L )
                                        .orElse( null ) );
                                studentPerson.setSpecChosen( studentPerson.getSpecialization() != null );
                                studentPerson.setSemester( 2L );
                                studentPerson.setBankAccountNumber( "12345678901234567890123456" );
                                student.setPerson( this.studentRepository.save( studentPerson ) );

                                User student2 = new User( 4L, "student2@student2.pl" );
                                student2.setRoles( new HashSet<>( Arrays.asList( roleStudent ) ) );
                                student2.setPassword( this.passwordEncoder.encode( "student2" ) );
                                student2.setPasswordConfirm( student.getPassword() );
                                Student studentPerson2 = new Student( 4L, "Studentdwa", "Studentdwa", true, 43567890123L );
                                studentPerson2.setAge( 20 );
                                studentPerson2.setCourse( this.courseRepository.findById( 3L )
                                        .orElse( null ) );
                                studentPerson2.setSpecialization( this.specializationRepository.findById( 2L )
                                    .orElse( null ) );
                                studentPerson2.setSpecChosen( studentPerson.getSpecialization() != null );
                                studentPerson2.setSemester( 2L );
                                studentPerson2.setBankAccountNumber( "22345678901234567890123456" );
                                student2.setPerson( this.studentRepository.save( studentPerson2 ) );






                                User terlik = new User( 5L, "terlik@o2.pl" );
                                terlik.setRoles( new HashSet<>( Arrays.asList( roleInstructor ) ) );
                                terlik.setPassword( this.passwordEncoder.encode( "xd" ) );
                                terlik.setPasswordConfirm( terlik.getPassword() );
                                Instructor terlikPerson = this.instructorRepository.save( new Instructor( 5L, "Grzegorz", "Terlikowski", true, 23456789012L ) );
                                terlik.setPerson( terlikPerson );

                                User skaruz = new User( 6L, "skaruz@o2.pl" );
                                skaruz.setRoles( new HashSet<>( Arrays.asList( roleInstructor ) ) );
                                skaruz.setPassword( this.passwordEncoder.encode( "xd" ) );
                                skaruz.setPasswordConfirm( skaruz.getPassword() );
                                Instructor skaruzPerson = this.instructorRepository.save( new Instructor( 6L, "Jarosław", "Skaruz", true, 23456789012L ) );
                                skaruz.setPerson( skaruzPerson );

                                User fizyk = new User( 7L, "fizyk@o2.pl" );
                                fizyk.setRoles( new HashSet<>( Arrays.asList( roleInstructor ) ) );
                                fizyk.setPassword( this.passwordEncoder.encode( "xd" ) );
                                fizyk.setPasswordConfirm( fizyk.getPassword() );
                                Instructor fizykPerson = this.instructorRepository.save( new Instructor( 7L, "Fizyk", "Fizyczny", true, 23456789012L ) );
                                fizyk.setPerson( fizykPerson );

                                User boczek = new User( 8L, "boczek@o2.pl" );
                                boczek.setRoles( new HashSet<>( Arrays.asList( roleStudent ) ) );
                                boczek.setPassword( this.passwordEncoder.encode( "xd" ) );
                                boczek.setPasswordConfirm( student.getPassword() );
                                Student boczekPerson = new Student( 8L, "Arnold", "Boczek", true, 43567890123L );
                                boczekPerson.setAge( 20 );
                                boczekPerson.setCourse( this.courseRepository.findById( 1L )
                                        .orElse( null ) );
                                boczekPerson.setSpecialization( this.specializationRepository.findById( 2L )
                                .orElse( null ) );
                                boczekPerson.setSpecChosen( studentPerson.getSpecialization() != null );
                                boczekPerson.setSemester( 4L );
                                boczekPerson.setBankAccountNumber( "22345678901234567890123456" );
                                boczek.setPerson( this.studentRepository.save( boczekPerson ) );

                                User komar = new User( 9L, "komar@o2.pl" );
                                komar.setRoles( new HashSet<>( Arrays.asList( roleStudent ) ) );
                                komar.setPassword( this.passwordEncoder.encode( "xd" ) );
                                komar.setPasswordConfirm( student.getPassword() );
                                Student komarPerson = new Student( 9L, "Maciej", "Goliszewski", true, 43567890123L );
                                komarPerson.setAge( 20 );
                                komarPerson.setCourse( this.courseRepository.findById( 1L )
                                        .orElse( null ) );
                                komarPerson.setSpecialization( this.specializationRepository.findById( 1L )
                                    .orElse( null ) );
                                komarPerson.setSpecChosen( studentPerson.getSpecialization() != null );
                                komarPerson.setSemester( 5L );
                                komarPerson.setBankAccountNumber( "22345678901234567890123456" );
                                komar.setPerson( this.studentRepository.save( komarPerson ) );

                                User ada = new User( 10L, "ada@o2.pl" );
                                ada.setRoles( new HashSet<>( Arrays.asList( roleStudent ) ) );
                                ada.setPassword( this.passwordEncoder.encode( "xd" ) );
                                ada.setPasswordConfirm( student.getPassword() );
                                Student adaPerson = new Student( 10L, "Ada", "Wójcik", true, 43567890123L );
                                adaPerson.setAge( 20 );
                                adaPerson.setCourse( this.courseRepository.findById( 1L )
                                        .orElse( null ) );
                                adaPerson.setSpecialization( this.specializationRepository.findById( 1L )
                                    .orElse( null ) );
                                adaPerson.setSpecChosen( studentPerson.getSpecialization() != null );
                                adaPerson.setSemester( 5L );
                                adaPerson.setBankAccountNumber( "22345678901234567890123456" );
                                ada.setPerson( this.studentRepository.save( adaPerson ) );





                                this.accountRepository.save( dean );
                                this.accountRepository.save( instructor );
                                this.accountRepository.save( student );
                                this.accountRepository.save( student2 );

                                this.accountRepository.save( terlik );
                                this.accountRepository.save( skaruz );
                                this.accountRepository.save( fizyk );
                                this.accountRepository.save( boczek );
                                this.accountRepository.save( komar );
                                this.accountRepository.save( ada );

                                if ( this.subjectRepository.findAll()
                                        .isEmpty() )
                                {
                                        this.subjectRepository.save( new Subject( 1L, "Inżynieria Oprogramowania", 5L, this.courseRepository.findById( 1L )
                                                .orElse( null ), skaruzPerson ) );
                                        this.subjectRepository.save( new Subject( 2L, "Platformy Programowania", 5L, this.courseRepository.findById( 1L )
                                                .orElse( null ), terlikPerson ) );
                                        this.subjectRepository.save( new Subject( 3L, "Programowanie Obiektowe", 2L, this.courseRepository.findById( 1L )
                                                .orElse( null ), skaruzPerson ) );
                                        this.subjectRepository.save( new Subject( 4L, "Systemy Wbudowane", 4L, this.courseRepository.findById( 1L )
                                                .orElse( null ), terlikPerson ) );
                                        this.subjectRepository.save( new Subject( 5L, "Algebra", 2L, this.courseRepository.findById( 2L )
                                                .orElse( null ), instructorPerson ) );
                                        this.subjectRepository.save( new Subject( 6L, "Podstawy Termodynamiki", 3L, this.courseRepository.findById( 4L )
                                                .orElse( null ), fizykPerson ) );
                                }


                        }
                };
        }

}