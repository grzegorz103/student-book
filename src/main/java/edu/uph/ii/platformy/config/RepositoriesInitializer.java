package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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

    private final ScholarshipRepository scholarshipRepository;

    private final UtilsRepository utilsRepository;

    @Autowired
    public RepositoriesInitializer ( InstructorRepository instructorRepository, DeanRepository deanRepository, StudentRepository studentRepository, RoleRepository roleRepository, CourseRepository courseRepository, SpecializationRepository specializationRepository, AccountRepository accountRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder, ScholarshipRepository scholarshipRepository, UtilsRepository utilsRepository )
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
        this.scholarshipRepository = scholarshipRepository;
        this.utilsRepository = utilsRepository;
    }

    @Bean
    InitializingBean init ()
    {
        return () -> {
            if ( this.utilsRepository.findAll ()
                    .isEmpty () )
            {
                this.utilsRepository.save ( new Utils ( 1L, true, true, true ) );
            }
            if ( this.courseRepository.findAll ()
                    .isEmpty () )
            {
                //KIERUNKI
                this.courseRepository.save ( new Course ( 1L, "Informatyka" ) );
                this.courseRepository.save ( new Course ( 2L, "Matematyka" ) );
                this.courseRepository.save ( new Course ( 3L, "Chemia" ) );
                this.courseRepository.save ( new Course ( 4L, "Fizyka" ) );
            }
            //SPECJALIZACJE
            if ( this.specializationRepository.findAll ()
                    .isEmpty () )
            {
                //INFORMATYKA
                this.specializationRepository.save ( new Specialization ( 1L, "Bazy danych", 20L, courseRepository.getOne ( 1L ) ) );
                this.specializationRepository.save ( new Specialization ( 2L, "Sieci komputerowe", 20L, courseRepository.getOne ( 1L ) ) );
                this.specializationRepository.save ( new Specialization ( 3L, "Gry i technologie internetowe", 20L, courseRepository.getOne ( 1L ) ) );
                //MATEMATYKA
                this.specializationRepository.save ( new Specialization ( 4L, "Matematyka finansowa", 20L, courseRepository.getOne ( 2L ) ) );
                this.specializationRepository.save ( new Specialization ( 5L, "Matematyka nauczycielska", 20L, courseRepository.getOne ( 2L ) ) );
                this.specializationRepository.save ( new Specialization ( 6L, "Matematyka teoretyczna", 20L, courseRepository.getOne ( 2L ) ) );
                //CHEMIA
                this.specializationRepository.save ( new Specialization ( 7L, "Biotechnologia", 20L, courseRepository.getOne ( 3L ) ) );
                this.specializationRepository.save ( new Specialization ( 8L, "Inżynieria materiałowa", 20L, courseRepository.getOne ( 3L ) ) );
                this.specializationRepository.save ( new Specialization ( 9L, "Technologia chemiczna", 20L, courseRepository.getOne ( 3L ) ) );
                //FIZYKA
                this.specializationRepository.save ( new Specialization ( 10L, "Biofizyka", 20L, courseRepository.getOne ( 4L ) ) );
                this.specializationRepository.save ( new Specialization ( 11L, "Fotonika", 20L, courseRepository.getOne ( 4L ) ) );
                this.specializationRepository.save ( new Specialization ( 12L, "Optyka", 20L, courseRepository.getOne ( 4L ) ) );
            }

            if ( this.roleRepository.findAll ()
                    .isEmpty () )
            {
                //ROLE
                Role roleStudent = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_STUDENT ) );
                Role roleInstructor = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_INSTRUCTOR ) );
                Role roleDean = this.roleRepository.save ( new Role ( Role.UserTypes.ROLE_DEAN ) );

                //DZIEKANI
                User dean = new User ( 1L, "dean@dean.pl" );
                dean.setRoles ( new HashSet<> ( Collections.singletonList ( roleDean ) ) );
                dean.setPassword ( this.passwordEncoder.encode ( "dean" ) );
                dean.setPasswordConfirm ( dean.getPassword () );
                dean.setPerson ( this.deanRepository.save ( new Dean ( 1L, "Dean", "Jeden", true, 12345678901L ) ) );

                //WYKŁADOWCY
                User instructor = new User ( 2L, "instructor@instructor.pl" );
                instructor.setRoles ( new HashSet<> ( Collections.singletonList ( roleInstructor ) ) );
                instructor.setPassword ( this.passwordEncoder.encode ( "instructor" ) );
                instructor.setPasswordConfirm ( instructor.getPassword () );
                Instructor instructorPerson = this.instructorRepository.save ( new Instructor ( 2L, "Instructor", "Jeden", true, 23456789012L ) );
                instructor.setPerson ( instructorPerson );

                User instructor2 = new User ( 3L, "instructor2@instructor2.pl" );
                instructor2.setRoles ( new HashSet<> ( Collections.singletonList ( roleInstructor ) ) );
                instructor2.setPassword ( this.passwordEncoder.encode ( "instructor2" ) );
                instructor2.setPasswordConfirm ( instructor2.getPassword () );
                Instructor instructorPerson2 = this.instructorRepository.save ( new Instructor ( 3L, "Instructor", "Dwa", true, 32345238902L ) );
                instructor2.setPerson ( instructorPerson2 );

                User instructor3 = new User ( 4L, "instructor3@instructor3.pl" );
                instructor3.setRoles ( new HashSet<> ( Collections.singletonList ( roleInstructor ) ) );
                instructor3.setPassword ( this.passwordEncoder.encode ( "instructor3" ) );
                instructor3.setPasswordConfirm ( instructor3.getPassword () );
                Instructor instructorPerson3 = this.instructorRepository.save ( new Instructor ( 4L, "Instructor", "Trzy", true, 23456789012L ) );
                instructor3.setPerson ( instructorPerson3 );

                User instructor4 = new User ( 5L, "instructor4@instructor4.pl" );
                instructor4.setRoles ( new HashSet<> ( Arrays.asList ( roleInstructor ) ) );
                instructor4.setPassword ( this.passwordEncoder.encode ( "instructor4" ) );
                instructor4.setPasswordConfirm ( instructor4.getPassword () );
                Instructor instructorPerson4 = this.instructorRepository.save ( new Instructor ( 5L, "Instructor", "Cztery", true, 23456789012L ) );
                instructor4.setPerson ( instructorPerson4 );

                User instructor5 = new User ( 6L, "instructor5@instructor5.pl" );
                instructor5.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor5.setPassword ( this.passwordEncoder.encode ( "instructor5" ) );
                instructor5.setPasswordConfirm ( instructor5.getPassword () );
                Instructor instructorPerson5 = this.instructorRepository.save ( new Instructor ( 6L, "Instructor", "Pięć", true, 23456789200L ) );
                instructor4.setPerson ( instructorPerson5 );

                User instructor6 = new User ( 7L, "instructor6@instructor6.pl" );
                instructor6.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor6.setPassword ( this.passwordEncoder.encode ( "instructor6" ) );
                instructor6.setPasswordConfirm ( instructor6.getPassword () );
                Instructor instructorPerson6 = this.instructorRepository.save ( new Instructor ( 7L, "Instructor", "Sześć", true, 23456789200L ) );
                instructor4.setPerson ( instructorPerson6 );

                User instructor7 = new User ( 8L, "instructor7@instructor7.pl" );
                instructor7.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor7.setPassword ( this.passwordEncoder.encode ( "instructor7" ) );
                instructor7.setPasswordConfirm ( instructor7.getPassword () );
                Instructor instructorPerson7 = this.instructorRepository.save ( new Instructor ( 8L, "Instructor", "Siedem", true, 23456789200L ) );
                instructor4.setPerson ( instructorPerson7 );

                User instructor8 = new User ( 9L, "instructor8@instructor8.pl" );
                instructor8.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor8.setPassword ( this.passwordEncoder.encode ( "instructor8" ) );
                instructor8.setPasswordConfirm ( instructor8.getPassword () );
                Instructor instructorPerson8 = this.instructorRepository.save ( new Instructor ( 9L, "Instructor", "Osiem", true, 23456789200L ) );
                instructor4.setPerson ( instructorPerson8 );

                //STUDENCI
                User student = new User ( 10L, "student@student.pl" );
                student.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student.setPassword ( this.passwordEncoder.encode ( "student" ) );
                student.setPasswordConfirm ( student.getPassword () );
                Student studentPerson = new Student ( 10L, "Student", "Jeden", true, 34567890123L );
                studentPerson.setAge ( 20 );
                studentPerson.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson.setSpecialization ( this.specializationRepository.getOne ( 1L ) );
                studentPerson.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson.setSemester ( 3L );
                studentPerson.setBankAccountNumber ( "12345678901234567890123456" );
                student.setPerson ( this.studentRepository.save ( studentPerson ) );

                User student2 = new User ( 11L, "student2@student2.pl" );
                student2.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student2.setPassword ( this.passwordEncoder.encode ( "student2" ) );
                student2.setPasswordConfirm ( student.getPassword () );
                Student studentPerson2 = new Student ( 11L, "Student", "Dwa", true, 45678901234L );
                studentPerson2.setAge ( 19 );
                studentPerson2.setCourse ( this.courseRepository.getOne ( 2L ) );
                studentPerson2.setSpecialization ( this.specializationRepository.getOne ( 4L ) );
                studentPerson2.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson2.setSemester ( 2L );
                studentPerson2.setBankAccountNumber ( "22345678901234567890123456" );
                student2.setPerson ( this.studentRepository.save ( studentPerson2 ) );

                User student3 = new User ( 12L, "student3@student3.pl" );
                student3.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student3.setPassword ( this.passwordEncoder.encode ( "student3" ) );
                student3.setPasswordConfirm ( student.getPassword () );
                Student studentPerson3 = new Student ( 12L, "Student", "Trzy", true, 56789012345L );
                studentPerson3.setAge ( 18 );
                studentPerson3.setCourse ( this.courseRepository.getOne ( 3L ) );
                studentPerson3.setSpecialization ( null );
                studentPerson3.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson3.setSemester ( 1L );
                studentPerson3.setBankAccountNumber ( "34567890123456789012345699" );
                student3.setPerson ( this.studentRepository.save ( studentPerson3 ) );

                User student4 = new User ( 13L, "student4@student4.pl" );
                student4.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student4.setPassword ( this.passwordEncoder.encode ( "student4" ) );
                student4.setPasswordConfirm ( student.getPassword () );
                Student studentPerson4 = new Student ( 13L, "Student", "Cztery", true, 67890123456L );
                studentPerson4.setAge ( 21 );
                studentPerson4.setCourse ( this.courseRepository.getOne ( 4L ) );
                studentPerson4.setSpecialization ( this.specializationRepository.getOne ( 10L ) );
                studentPerson4.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson4.setSemester ( 4L );
                studentPerson4.setBankAccountNumber ( "36789012345678900912345699" );
                student4.setPerson ( this.studentRepository.save ( studentPerson4 ) );

                User student5 = new User ( 14L, "student5@student5.pl" );
                student5.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student5.setPassword ( this.passwordEncoder.encode ( "student5" ) );
                student5.setPasswordConfirm ( student.getPassword () );
                Student studentPerson5 = new Student ( 14L, "Student", "Pięć", true, 43567890123L );
                studentPerson5.setAge ( 22 );
                studentPerson5.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson5.setSpecialization ( null );
                studentPerson5.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson5.setSemester ( 5L );
                studentPerson5.setBankAccountNumber ( "64223678901234567890123456" );
                student5.setPerson ( this.studentRepository.save ( studentPerson5 ) );

                User student6 = new User ( 15L, "student6@student6.pl" );
                student6.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student6.setPassword ( this.passwordEncoder.encode ( "student6" ) );
                student6.setPasswordConfirm ( student.getPassword () );
                Student studentPerson6 = new Student ( 15L, "Ada", "Wójcik", true, 43567890123L );
                studentPerson6.setAge ( 23 );
                studentPerson6.setCourse ( this.courseRepository.getOne ( 2L ) );
                studentPerson6.setSpecialization ( null );
                studentPerson6.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson6.setSemester ( 6L );
                studentPerson6.setBankAccountNumber ( "22345678901234569012345616" );
                student6.setPerson ( this.studentRepository.save ( studentPerson6 ) );

                this.accountRepository.save ( dean );
                this.accountRepository.save ( instructor );
                this.accountRepository.save ( instructor2 );
                this.accountRepository.save ( instructor3 );
                this.accountRepository.save ( instructor4 );
                this.accountRepository.save ( instructor5 );
                this.accountRepository.save ( instructor6 );
                this.accountRepository.save ( instructor7 );
                this.accountRepository.save ( instructor8 );
                this.accountRepository.save ( student );
                this.accountRepository.save ( student2 );
                this.accountRepository.save ( student3 );
                this.accountRepository.save ( student4 );
                this.accountRepository.save ( student5 );
                this.accountRepository.save ( student6 );

                //PRZEDMIOTY
                if ( this.subjectRepository.findAll ()
                        .isEmpty () )
                {
                    //informatyka sem 1
                    this.subjectRepository.save ( new Subject ( 1L, "Wstęp do programowania", 1L, this.courseRepository.getOne ( 1L ), instructorPerson ) );
                    this.subjectRepository.save ( new Subject ( 2L, "Analiza matematyczna", 1L, this.courseRepository.getOne ( 1L ), instructorPerson2 ) );
                    //informatyka sem 2
                    this.subjectRepository.save ( new Subject ( 3L, "Programowanie Obiektowe", 2L, this.courseRepository.getOne ( 1L ), instructorPerson3 ) );
                    this.subjectRepository.save ( new Subject ( 4L, "Algorytmy i struktury danych", 2L, this.courseRepository.getOne ( 1L ), instructorPerson4 ) );
                    //informatyka sem 3
                    this.subjectRepository.save ( new Subject ( 5L, "Inżynieria Oprogramowania", 3L, this.courseRepository.getOne ( 1L ), instructorPerson8 ) );
                    this.subjectRepository.save ( new Subject ( 6L, "Platformy Programowania", 3L, this.courseRepository.getOne ( 1L ), instructorPerson ) );
                    //informatyka sem 4
                    this.subjectRepository.save ( new Subject ( 7L, "Systemy Wbudowane", 4L, this.courseRepository.getOne ( 1L ), instructorPerson3 ) );
                    this.subjectRepository.save ( new Subject ( 8L, "Algebra", 4L, this.courseRepository.getOne ( 1L ), instructorPerson2 ) );
                    //informatyka sem 5
                    this.subjectRepository.save ( new Subject ( 9L, "Programowanie niskopoziomowe", 5L, this.courseRepository.getOne ( 1L ), instructorPerson3 ) );
                    this.subjectRepository.save ( new Subject ( 10L, "Systemy operacyjne", 5L, this.courseRepository.getOne ( 1L ), instructorPerson8 ) );
                    //informatyka sem 6
                    this.subjectRepository.save ( new Subject ( 11L, "Aplikacje WWW", 6L, this.courseRepository.getOne ( 1L ), instructorPerson4 ) );
                    this.subjectRepository.save ( new Subject ( 12L, "Grafika komputerowa i wizualizacja", 6L, this.courseRepository.getOne ( 1L ), instructorPerson8 ) );

                    //matematyka sem 1
                    this.subjectRepository.save ( new Subject ( 13L, "Analiza matematyczna", 1L, this.courseRepository.getOne ( 2L ), instructorPerson2 ) );
                    this.subjectRepository.save ( new Subject ( 14L, "Rachunek prawdopodobieństwa", 1L, this.courseRepository.getOne ( 2L ), instructorPerson6 ) );
                    //matematyka sem 2
                    this.subjectRepository.save ( new Subject ( 15L, "Algebra", 2L, this.courseRepository.getOne ( 2L ), instructorPerson2 ) );
                    this.subjectRepository.save ( new Subject ( 16L, "Matematyka dyskretna", 2L, this.courseRepository.getOne ( 2L ), instructorPerson7 ) );
                    //matematyka sem 3
                    this.subjectRepository.save ( new Subject ( 17L, "Równania różniczkowe zwyczajne", 3L, this.courseRepository.getOne ( 2L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 18L, "Statystyka", 3L, this.courseRepository.getOne ( 2L ), instructorPerson8 ) );
                    //matematyka sem 4
                    this.subjectRepository.save ( new Subject ( 19L, "Języki, automaty i obliczenia", 4L, this.courseRepository.getOne ( 2L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 20L, "Metody numeryczne", 4L, this.courseRepository.getOne ( 2L ), instructorPerson2 ) );
                    //matematyka sem 5
                    this.subjectRepository.save ( new Subject ( 21L, "Geometria z algebrą liniową", 5L, this.courseRepository.getOne ( 2L ), instructorPerson8 ) );
                    this.subjectRepository.save ( new Subject ( 22L, "Topologia", 5L, this.courseRepository.getOne ( 2L ), instructorPerson7 ) );
                    //matematyka sem 6
                    this.subjectRepository.save ( new Subject ( 23L, "Geometria z algebrą liniową", 6L, this.courseRepository.getOne ( 2L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 24L, "Matematyka obliczeniowa", 6L, this.courseRepository.getOne ( 2L ), instructorPerson7 ) );

                    //chemia sem 1
                    this.subjectRepository.save ( new Subject ( 25L, "Podstawy chemii", 1L, this.courseRepository.getOne ( 3L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 26L, "Matematyka", 1L, this.courseRepository.getOne ( 3L ), instructorPerson7 ) );
                    //chemia sem 2
                    this.subjectRepository.save ( new Subject ( 27L, "Rysunek techniczny", 2L, this.courseRepository.getOne ( 3L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 28L, "Chemia ogólna i nieorganiczna", 2L, this.courseRepository.getOne ( 3L ), instructorPerson7 ) );
                    //chemia sem 3
                    this.subjectRepository.save ( new Subject ( 29L, "Chemia kwantowa", 3L, this.courseRepository.getOne ( 3L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 30L, "Chemia organiczna", 3L, this.courseRepository.getOne ( 3L ), instructorPerson7 ) );
                    //chemia sem 4
                    this.subjectRepository.save ( new Subject ( 31L, "Kreatywność biznesowa", 4L, this.courseRepository.getOne ( 3L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 32L, "Chemia analityczna", 4L, this.courseRepository.getOne ( 3L ), instructorPerson8 ) );
                    //chemia sem 5
                    this.subjectRepository.save ( new Subject ( 33L, "Aparatura chemiczna", 5L, this.courseRepository.getOne ( 3L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 34L, "Biochemia", 5L, this.courseRepository.getOne ( 3L ), instructorPerson7 ) );
                    //chemia sem 6
                    this.subjectRepository.save ( new Subject ( 35L, "Chemia praktyczna", 6L, this.courseRepository.getOne ( 3L ), instructorPerson7 ) );
                    this.subjectRepository.save ( new Subject ( 36L, "Technologia chemiczna", 6L, this.courseRepository.getOne ( 3L ), instructorPerson8 ) );

                    //fizyka sem 1
                    this.subjectRepository.save ( new Subject ( 37L, "Fizyka", 1L, this.courseRepository.getOne ( 4L ), instructorPerson4 ) );
                    this.subjectRepository.save ( new Subject ( 38L, "Matematyka", 1L, this.courseRepository.getOne ( 4L ), instructorPerson7 ) );
                    //fizyka sem 2
                    this.subjectRepository.save ( new Subject ( 39L, "Analiza niepewności pomiarowych i pracownia wstępna", 2L, this.courseRepository.getOne ( 4L ), instructorPerson8 ) );
                    this.subjectRepository.save ( new Subject ( 40L, "Mechanika klasyczna", 2L, this.courseRepository.getOne ( 4L ), instructorPerson2 ) );
                    //fizyka sem 3
                    this.subjectRepository.save ( new Subject ( 41L, "Pracownia technik pomiarowych", 3L, this.courseRepository.getOne ( 4L ), instructorPerson ) );
                    this.subjectRepository.save ( new Subject ( 42L, "Elektrodynamika", 3L, this.courseRepository.getOne ( 4L ), instructorPerson3 ) );
                    //fizyka sem 4
                    this.subjectRepository.save ( new Subject ( 43L, "Pracownia fizyczna dla zaawansowanych", 4L, this.courseRepository.getOne ( 4L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 44L, "Indywidualna pracownia", 4L, this.courseRepository.getOne ( 4L ), instructorPerson5 ) );
                    //fizyka sem 5
                    this.subjectRepository.save ( new Subject ( 45L, "Wstęp do fizyki subatomowej", 5L, this.courseRepository.getOne ( 4L ), instructorPerson2 ) );
                    this.subjectRepository.save ( new Subject ( 46L, "Wstęp do optyki i fizyki materii skondensowanej", 5L, this.courseRepository.getOne ( 4L ), instructorPerson ) );
                    //fizyka sem 6
                    this.subjectRepository.save ( new Subject ( 47L, "Indywidualna pracownia", 6L, this.courseRepository.getOne ( 4L ), instructorPerson7 ) );
                    this.subjectRepository.save ( new Subject ( 48L, "Indywidualna praca w laboratorium badawczym", 6L, this.courseRepository.getOne ( 4L ), instructorPerson8 ) );
                }

                if ( this.scholarshipRepository.findAll ()
                        .isEmpty () )
                {
                    this.scholarshipRepository.save ( new Scholarship ( 1L, ScholarshipTypes.SOCIAL, 2, new BigDecimal ( 3000.00 ), new BigDecimal ( 0.00 ), 0.0d, 3L, new Date ( 119, 1, 12 ), null, Statuses.AWAITING, ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                            .getPerson () ) );
                    this.scholarshipRepository.save ( new Scholarship ( 2L, ScholarshipTypes.SOCIAL, 1, new BigDecimal ( 1000.00 ), new BigDecimal ( 500.00 ), 0.0d, 2L, new Date ( 119, 1, 13 ), new Date ( 119, 1, 14 ), Statuses.ACCEPTED, ( Student ) this.accountRepository.findByMail ( "student2@student2.pl" )
                            .getPerson () ) );
                }
//TODO dodawanie oceny
            }
        };
    }

}