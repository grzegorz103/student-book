package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

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
    private final AttendanceRepository attendanceRepository;
    private final LessonRepository lessonRepository;
    private final OpinionRepository opinionRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    private final ScholarshipRepository scholarshipRepository;
    private final SemestralGradesRepository semestralGradesRepository;
    private final UtilsRepository utilsRepository;
    private final WorkshopRepository workshopRepository;

    @Autowired
    public RepositoriesInitializer ( InstructorRepository instructorRepository, DeanRepository deanRepository, StudentRepository studentRepository, RoleRepository roleRepository, CourseRepository courseRepository, SpecializationRepository specializationRepository, AccountRepository accountRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder, ScholarshipRepository scholarshipRepository, UtilsRepository utilsRepository, SemestralGradesRepository semestralGradesRepository, LessonRepository lessonRepository, AttendanceRepository attendanceRepository, OpinionRepository opinionRepository, WorkshopRepository workshopRepository )
    {
        this.instructorRepository = instructorRepository;
        this.deanRepository = deanRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.specializationRepository = specializationRepository;
        this.accountRepository = accountRepository;
        this.lessonRepository = lessonRepository;
        this.opinionRepository = opinionRepository;
        this.attendanceRepository = attendanceRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
        this.scholarshipRepository = scholarshipRepository;
        this.utilsRepository = utilsRepository;
        this.semestralGradesRepository = semestralGradesRepository;
        this.workshopRepository = workshopRepository;
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
                Instructor instructorPerson = this.instructorRepository.save ( new Instructor ( 2L, "Jan", "Kowalski", true, 23456789012L ) );
                instructor.setPerson ( instructorPerson );

                User instructor2 = new User ( 3L, "instructor2@instructor2.pl" );
                instructor2.setRoles ( new HashSet<> ( Collections.singletonList ( roleInstructor ) ) );
                instructor2.setPassword ( this.passwordEncoder.encode ( "instructor2" ) );
                instructor2.setPasswordConfirm ( instructor2.getPassword () );
                Instructor instructorPerson2 = this.instructorRepository.save ( new Instructor ( 3L, "Przemysław", "Nowak", true, 32345238902L ) );
                instructor2.setPerson ( instructorPerson2 );

                User instructor3 = new User ( 4L, "instructor3@instructor3.pl" );
                instructor3.setRoles ( new HashSet<> ( Collections.singletonList ( roleInstructor ) ) );
                instructor3.setPassword ( this.passwordEncoder.encode ( "instructor3" ) );
                instructor3.setPasswordConfirm ( instructor3.getPassword () );
                Instructor instructorPerson3 = this.instructorRepository.save ( new Instructor ( 4L, "Marek", "Knap", true, 23456789012L ) );
                instructor3.setPerson ( instructorPerson3 );

                User instructor4 = new User ( 5L, "instructor4@instructor4.pl" );
                instructor4.setRoles ( new HashSet<> ( Arrays.asList ( roleInstructor ) ) );
                instructor4.setPassword ( this.passwordEncoder.encode ( "instructor4" ) );
                instructor4.setPasswordConfirm ( instructor4.getPassword () );
                Instructor instructorPerson4 = this.instructorRepository.save ( new Instructor ( 5L, "Wojciech", "Bendyk", true, 23456789012L ) );
                instructor4.setPerson ( instructorPerson4 );

                User instructor5 = new User ( 6L, "instructor5@instructor5.pl" );
                instructor5.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor5.setPassword ( this.passwordEncoder.encode ( "instructor5" ) );
                instructor5.setPasswordConfirm ( instructor5.getPassword () );
                Instructor instructorPerson5 = this.instructorRepository.save ( new Instructor ( 6L, "Janusz", "Wójcik", true, 23456789200L ) );
                instructor5.setPerson ( instructorPerson5 );

                User instructor6 = new User ( 7L, "instructor6@instructor6.pl" );
                instructor6.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor6.setPassword ( this.passwordEncoder.encode ( "instructor6" ) );
                instructor6.setPasswordConfirm ( instructor6.getPassword () );
                Instructor instructorPerson6 = this.instructorRepository.save ( new Instructor ( 7L, "Anna", "Jaworska", true, 23456789200L ) );
                instructor6.setPerson ( instructorPerson6 );

                User instructor7 = new User ( 8L, "instructor7@instructor7.pl" );
                instructor7.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor7.setPassword ( this.passwordEncoder.encode ( "instructor7" ) );
                instructor7.setPasswordConfirm ( instructor7.getPassword () );
                Instructor instructorPerson7 = this.instructorRepository.save ( new Instructor ( 8L, "Edyta", "Wasilewska", true, 23456789200L ) );
                instructor7.setPerson ( instructorPerson7 );

                User instructor8 = new User ( 9L, "instructor8@instructor8.pl" );
                instructor8.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                instructor8.setPassword ( this.passwordEncoder.encode ( "instructor8" ) );
                instructor8.setPasswordConfirm ( instructor8.getPassword () );
                Instructor instructorPerson8 = this.instructorRepository.save ( new Instructor ( 9L, "Maria", "Szymkowiak", true, 23456789200L ) );
                instructor8.setPerson ( instructorPerson8 );

                //STUDENCI
                User student = new User ( 10L, "student@student.pl" );
                student.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student.setPassword ( this.passwordEncoder.encode ( "student" ) );
                student.setPasswordConfirm ( student.getPassword () );
                Student studentPerson = new Student ( 10L, "Arkadiusz", "Celiński", true, 34567890123L );
                studentPerson.setAge ( 20 );
                studentPerson.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson.setSpecialization ( this.specializationRepository.getOne ( 1L ) );
                studentPerson.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson.setSemester ( 1L );
                studentPerson.setBankAccountNumber ( "12345678901234567890123456" );
                student.setPerson ( this.studentRepository.save ( studentPerson ) );

                User student2 = new User ( 11L, "student2@student2.pl" );
                student2.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student2.setPassword ( this.passwordEncoder.encode ( "student2" ) );
                student2.setPasswordConfirm ( student.getPassword () );
                Student studentPerson2 = new Student ( 11L, "Mariusz", "Koziński", true, 45678901234L );
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
                Student studentPerson3 = new Student ( 12L, "Patryk", "Siński", true, 56789012345L );
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
                Student studentPerson4 = new Student ( 13L, "Piotr", "Krawczyk", true, 67890123456L );
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
                Student studentPerson5 = new Student ( 14L, "Magdalena", "Janota", true, 43567890123L );
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

                User student7 = new User ( 16L, "student7@student7.pl" );
                student7.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student7.setPassword ( this.passwordEncoder.encode ( "student7" ) );
                student7.setPasswordConfirm ( student.getPassword () );
                Student studentPerson7 = new Student ( 16L, "Ola", "Dąbrowska", true, 43567890123L );
                studentPerson7.setAge ( 23 );
                studentPerson7.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson7.setSpecialization ( null );
                studentPerson7.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson7.setSemester ( 1L );
                studentPerson7.setBankAccountNumber ( "22345678901234569012345616" );
                student7.setPerson ( this.studentRepository.save ( studentPerson7 ) );

                User student8 = new User ( 17L, "student8@student8.pl" );
                student8.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student8.setPassword ( this.passwordEncoder.encode ( "student8" ) );
                student8.setPasswordConfirm ( student.getPassword () );
                Student studentPerson8 = new Student ( 17L, "Maciej", "Jesion", true, 43567890123L );
                studentPerson8.setAge ( 23 );
                studentPerson8.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson8.setSpecialization ( null );
                studentPerson8.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson8.setSemester ( 1L );
                studentPerson8.setBankAccountNumber ( "22345678901234569012345616" );
                student8.setPerson ( this.studentRepository.save ( studentPerson8 ) );

                User student9 = new User ( 18L, "student9@student9.pl" );
                student9.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student9.setPassword ( this.passwordEncoder.encode ( "student9" ) );
                student9.setPasswordConfirm ( student.getPassword () );
                Student studentPerson9 = new Student ( 18L, "Filip", "Kamiński", true, 43567890123L );
                studentPerson9.setAge ( 23 );
                studentPerson9.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson9.setSpecialization ( null );
                studentPerson9.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson9.setSemester ( 1L );
                studentPerson9.setBankAccountNumber ( "22345678901234569012345616" );
                student9.setPerson ( this.studentRepository.save ( studentPerson9 ) );

                User student10 = new User ( 19L, "student10@student10.pl" );
                student10.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student10.setPassword ( this.passwordEncoder.encode ( "student10" ) );
                student10.setPasswordConfirm ( student.getPassword () );
                Student studentPerson10 = new Student ( 19L, "Kasia", "Moniuszko", true, 43567890123L );
                studentPerson10.setAge ( 23 );
                studentPerson10.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson10.setSpecialization ( null );
                studentPerson10.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson10.setSemester ( 1L );
                studentPerson10.setBankAccountNumber ( "22345678901234569012345616" );
                student10.setPerson ( this.studentRepository.save ( studentPerson10 ) );

                User student11 = new User ( 20L, "student11@student11.pl" );
                student11.setRoles ( new HashSet<> ( Collections.singletonList ( roleStudent ) ) );
                student11.setPassword ( this.passwordEncoder.encode ( "student11" ) );
                student11.setPasswordConfirm ( student.getPassword () );
                Student studentPerson11 = new Student ( 20L, "Leszek", "Olbracht", true, 43567890123L );
                studentPerson11.setAge ( 23 );
                studentPerson11.setCourse ( this.courseRepository.getOne ( 1L ) );
                studentPerson11.setSpecialization ( null );
                studentPerson11.setSpecChosen ( studentPerson.getSpecialization () != null );
                studentPerson11.setSemester ( 1L );
                studentPerson11.setBankAccountNumber ( "22345678901234569012345616" );
                student11.setPerson ( this.studentRepository.save ( studentPerson11 ) );

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
                this.accountRepository.save ( student7 );
                this.accountRepository.save ( student8 );
                this.accountRepository.save ( student9 );
                this.accountRepository.save ( student10 );
                this.accountRepository.save ( student11 );

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
                    this.subjectRepository.save ( new Subject ( 8L, "Algebra", 4L, this.courseRepository.getOne ( 1L ), instructorPerson4 ) );
                    //informatyka sem 5
                    this.subjectRepository.save ( new Subject ( 9L, "Programowanie niskopoziomowe", 5L, this.courseRepository.getOne ( 1L ), instructorPerson3 ) );
                    this.subjectRepository.save ( new Subject ( 10L, "Systemy operacyjne", 5L, this.courseRepository.getOne ( 1L ), instructorPerson8 ) );
                    //informatyka sem 6
                    this.subjectRepository.save ( new Subject ( 11L, "Aplikacje WWW", 6L, this.courseRepository.getOne ( 1L ), instructorPerson4 ) );
                    this.subjectRepository.save ( new Subject ( 12L, "Grafika komputerowa i wizualizacja", 6L, this.courseRepository.getOne ( 1L ), instructorPerson8 ) );

                    //matematyka sem 1
                    this.subjectRepository.save ( new Subject ( 13L, "Analiza matematyczna", 1L, this.courseRepository.getOne ( 2L ), instructorPerson4 ) );
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
                    this.subjectRepository.save ( new Subject ( 40L, "Mechanika klasyczna", 2L, this.courseRepository.getOne ( 4L ), instructorPerson4 ) );
                    //fizyka sem 3
                    this.subjectRepository.save ( new Subject ( 41L, "Pracownia technik pomiarowych", 3L, this.courseRepository.getOne ( 4L ), instructorPerson5 ) );
                    this.subjectRepository.save ( new Subject ( 42L, "Elektrodynamika", 3L, this.courseRepository.getOne ( 4L ), instructorPerson3 ) );
                    //fizyka sem 4
                    this.subjectRepository.save ( new Subject ( 43L, "Pracownia fizyczna dla zaawansowanych", 4L, this.courseRepository.getOne ( 4L ), instructorPerson6 ) );
                    this.subjectRepository.save ( new Subject ( 44L, "Indywidualna pracownia", 4L, this.courseRepository.getOne ( 4L ), instructorPerson5 ) );
                    //fizyka sem 5
                    this.subjectRepository.save ( new Subject ( 45L, "Wstęp do fizyki subatomowej", 5L, this.courseRepository.getOne ( 4L ), instructorPerson4 ) );
                    this.subjectRepository.save ( new Subject ( 46L, "Wstęp do optyki i fizyki materii skondensowanej", 5L, this.courseRepository.getOne ( 4L ), instructorPerson5 ) );
                    //fizyka sem 6
                    this.subjectRepository.save ( new Subject ( 47L, "Indywidualna pracownia", 6L, this.courseRepository.getOne ( 4L ), instructorPerson7 ) );
                    this.subjectRepository.save ( new Subject ( 48L, "Indywidualna praca w laboratorium badawczym", 6L, this.courseRepository.getOne ( 4L ), instructorPerson8 ) );
                }

                //LEKCJE
                if ( this.lessonRepository.findAll ()
                        .isEmpty () ) {

                    //Wstęp do programowania
                    this.lessonRepository.save(new Lesson(1L, new java.sql.Date(118, 10, 3), this.subjectRepository.getOne(1L)));
                    this.lessonRepository.save(new Lesson(2L, new java.sql.Date(118, 10, 10), this.subjectRepository.getOne(1L)));
                    this.lessonRepository.save(new Lesson(3L, new java.sql.Date(118, 10, 17), this.subjectRepository.getOne(1L)));
                    this.lessonRepository.save(new Lesson(4L, new java.sql.Date(118, 10, 24), this.subjectRepository.getOne(1L)));
                }

                //OBECNOŚCI
                if ( this.attendanceRepository.findAll ()
                        .isEmpty () ) {

                    //Wstęp do programowania L1
                    this.attendanceRepository.save(new Attendance(1L, ( Student ) this.accountRepository.findByMail( "student@student.pl" ).getPerson (), this.lessonRepository.getOne(1L), true));
                    this.attendanceRepository.save(new Attendance(2L, ( Student ) this.accountRepository.findByMail( "student7@student7.pl" ).getPerson (), this.lessonRepository.getOne(1L), true));
                    this.attendanceRepository.save(new Attendance(3L, ( Student ) this.accountRepository.findByMail( "student8@student8.pl" ).getPerson (), this.lessonRepository.getOne(1L), true));
                    this.attendanceRepository.save(new Attendance(4L, ( Student ) this.accountRepository.findByMail( "student9@student9.pl" ).getPerson (), this.lessonRepository.getOne(1L), true));
                    this.attendanceRepository.save(new Attendance(5L, ( Student ) this.accountRepository.findByMail( "student10@student10.pl" ).getPerson (), this.lessonRepository.getOne(1L), true));
                    this.attendanceRepository.save(new Attendance(6L, ( Student ) this.accountRepository.findByMail( "student11@student11.pl" ).getPerson (), this.lessonRepository.getOne(1L), false));

                }

                //OPINIE
                if ( this.opinionRepository.findAll ()
                        .isEmpty () ) {

                    //Jan Kowalski
                    this.opinionRepository.save(new Opinion(1L, ( Instructor ) this.accountRepository.findByMail( "instructor@instructor.pl" ).getPerson (), this.subjectRepository.getOne(1L), ( Student ) this.accountRepository.findByMail( "student@student.pl" ).getPerson (), Statuses.ACCEPTED, "Super wykładowca, dzięki niemu mam 5 z egzaminu!!!"));
                    this.opinionRepository.save(new Opinion(2L, ( Instructor ) this.accountRepository.findByMail( "instructor@instructor.pl" ).getPerson (), this.subjectRepository.getOne(6L), ( Student ) this.accountRepository.findByMail( "student7@student7.pl" ).getPerson (), Statuses.ACCEPTED, "Prowadzone przez tego pana wykłady z platform programowania są bardzo nudne i męczące. Nie polecam!"));

                    //Przemysław Nowak
                    this.opinionRepository.save(new Opinion(3L, ( Instructor ) this.accountRepository.findByMail( "instructor2@instructor2.pl" ).getPerson (), this.subjectRepository.getOne(2L), ( Student ) this.accountRepository.findByMail( "student8@student8.pl" ).getPerson (), Statuses.REJECTED, "Ale dzban xDDDD"));
                    this.opinionRepository.save(new Opinion(4L, ( Instructor ) this.accountRepository.findByMail( "instructor2@instructor2.pl" ).getPerson (), this.subjectRepository.getOne(2L), ( Student ) this.accountRepository.findByMail( "student9@student9.pl" ).getPerson (), Statuses.AWAITING, "Często się zdarza, że nie odpisuje wcale na maile i nie ma z nim żadnego kontaktu..."));
                }

                //WARSZTATY
                if ( this.workshopRepository.findAll ()
                        .isEmpty () ) {

                    //Spring Boot
                    Workshop warsztat1 = new Workshop();

                    warsztat1.setId(1L);
                    warsztat1.setName("Kurs Spring Boot");
                    warsztat1.setLimit(10L);
                    warsztat1.setInstructor(( Instructor ) this.accountRepository.findByMail( "instructor@instructor.pl" ).getPerson ());

                    warsztat1.setStudents(new HashSet<>());
                    warsztat1.getStudents().add(( Student ) this.accountRepository.findByMail( "student@student.pl" ).getPerson ());
                    warsztat1.getStudents().add(( Student ) this.accountRepository.findByMail( "student2@student2.pl" ).getPerson ());
                    warsztat1.getStudents().add(( Student ) this.accountRepository.findByMail( "student3@student3.pl" ).getPerson ());
                    warsztat1.getStudents().add(( Student ) this.accountRepository.findByMail( "student4@student4.pl" ).getPerson ());
                    warsztat1.getStudents().add(( Student ) this.accountRepository.findByMail( "student5@student5.pl" ).getPerson ());

                    warsztat1.setUnits(new HashSet<>());
                    warsztat1.getUnits().add(new Unit(1L, "Lekcja 1 - Początki", warsztat1, "Wprowadzenie do Spring Framework i Spring MVC", "https://mirek.ii.uph.edu.pl/m3d/lab4n.doc"));
                    warsztat1.getUnits().add(new Unit(2L, "Lekcja 2 - Maven", warsztat1, "Wprowadzenie do Maven’a i praca ze statycznymi zasobami", "https://mirek.ii.uph.edu.pl/m3d/lab4n.doc"));
                    warsztat1.getUnits().add(new Unit(3L, "Lekcja 3 - Formularze", warsztat1, "Obsługa formularzy – zależności formularza i walidacja danych", "https://mirek.ii.uph.edu.pl/m3d/lab4n.doc"));

                    this.workshopRepository.save(warsztat1);

                    //GIT
                    Workshop warsztat2 = new Workshop();

                    warsztat2.setId(2L);
                    warsztat2.setName("Kurs GITa");
                    warsztat2.setLimit(10L);
                    warsztat2.setInstructor(( Instructor ) this.accountRepository.findByMail( "instructor@instructor.pl" ).getPerson ());

                    warsztat2.setStudents(new HashSet<>());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student2@student2.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student3@student3.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student4@student4.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student5@student5.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student6@student6.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student7@student7.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student8@student8.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student9@student9.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student10@student10.pl" ).getPerson ());
                    warsztat2.getStudents().add(( Student ) this.accountRepository.findByMail( "student11@student11.pl" ).getPerson ());

                    warsztat2.setUnits(new HashSet<>());

                    this.workshopRepository.save(warsztat2);

                    //Arduino
                    Workshop warsztat3 = new Workshop();

                    warsztat3.setId(3L);
                    warsztat3.setName("Kurs Arduino");
                    warsztat3.setLimit(15L);
                    warsztat3.setInstructor(( Instructor ) this.accountRepository.findByMail( "instructor@instructor.pl" ).getPerson ());
                    warsztat3.setStudents(new HashSet<>());
                    warsztat3.setUnits(new HashSet<>());

                    this.workshopRepository.save(warsztat3);

                }


                //OCENY
                if ( this.semestralGradesRepository.findAll ()
                        .isEmpty () )
                {
                    //student sem 1
                    this.semestralGradesRepository.save ( new SemestralGrade ( 1L, this.subjectRepository.getOne ( 1L ), ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                            .getPerson (), 4L, null, 4L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 2L, this.subjectRepository.getOne ( 2L ), ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    //student sem 2
                    this.semestralGradesRepository.save ( new SemestralGrade ( 3L, this.subjectRepository.getOne ( 3L ), ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 4L, this.subjectRepository.getOne ( 4L ), ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                            .getPerson (), 3L, null, 3L ) );

                    //student2 sem 1
                    this.semestralGradesRepository.save ( new SemestralGrade ( 5L, this.subjectRepository.getOne ( 13L ), ( Student ) this.accountRepository.findByMail ( "student2@student2.pl" )
                            .getPerson (), 2L, 2L, 2L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 6L, this.subjectRepository.getOne ( 14L ), ( Student ) this.accountRepository.findByMail ( "student2@student2.pl" )
                            .getPerson (), 2L, 4L, 4L ) );

                    //student3 brak ocen (semestr 1)

                    //student4 sem 1
                    this.semestralGradesRepository.save ( new SemestralGrade ( 7L, this.subjectRepository.getOne ( 37L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 8L, this.subjectRepository.getOne ( 38L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    //student4 sem 2
                    this.semestralGradesRepository.save ( new SemestralGrade ( 9L, this.subjectRepository.getOne ( 39L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 2L, 4L, 4L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 10L, this.subjectRepository.getOne ( 40L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    //student4 sem 3
                    this.semestralGradesRepository.save ( new SemestralGrade ( 11L, this.subjectRepository.getOne ( 41L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 12L, this.subjectRepository.getOne ( 42L ), ( Student ) this.accountRepository.findByMail ( "student4@student4.pl" )
                            .getPerson (), 2L, null, 2L ) );

                    //student5 sem 1
                    this.semestralGradesRepository.save ( new SemestralGrade ( 13L, this.subjectRepository.getOne ( 1L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 14L, this.subjectRepository.getOne ( 2L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 4L, null, 4L ) );
                    //student5 sem 2
                    this.semestralGradesRepository.save ( new SemestralGrade ( 15L, this.subjectRepository.getOne ( 3L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 16L, this.subjectRepository.getOne ( 4L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 4L, null, 4L ) );
                    //student5 sem 3
                    this.semestralGradesRepository.save ( new SemestralGrade ( 17L, this.subjectRepository.getOne ( 5L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 2L, 3L, 3L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 18L, this.subjectRepository.getOne ( 6L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    //student5 sem 4
                    this.semestralGradesRepository.save ( new SemestralGrade ( 19L, this.subjectRepository.getOne ( 7L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 20L, this.subjectRepository.getOne ( 8L ), ( Student ) this.accountRepository.findByMail ( "student5@student5.pl" )
                            .getPerson (), 3L, null, 3L ) );

                    //student6 sem 1
                    this.semestralGradesRepository.save ( new SemestralGrade ( 21L, this.subjectRepository.getOne ( 13L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 22L, this.subjectRepository.getOne ( 14L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    //student6 sem 2
                    this.semestralGradesRepository.save ( new SemestralGrade ( 23L, this.subjectRepository.getOne ( 15L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 2L, 5L, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 24L, this.subjectRepository.getOne ( 16L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 4L, null, 4L ) );
                    //student6 sem 3
                    this.semestralGradesRepository.save ( new SemestralGrade ( 25L, this.subjectRepository.getOne ( 17L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 26L, this.subjectRepository.getOne ( 18L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 5L, null, 5L ) );
                    //student6 sem 4
                    this.semestralGradesRepository.save ( new SemestralGrade ( 27L, this.subjectRepository.getOne ( 19L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 4L, null, 4L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 28L, this.subjectRepository.getOne ( 20L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 3L, null, 3L ) );
                    //student6 sem 5
                    this.semestralGradesRepository.save ( new SemestralGrade ( 29L, this.subjectRepository.getOne ( 21L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 2L, null, 2L ) );
                    this.semestralGradesRepository.save ( new SemestralGrade ( 30L, this.subjectRepository.getOne ( 22L ), ( Student ) this.accountRepository.findByMail ( "student6@student6.pl" )
                            .getPerson (), 2L, null, 2L ) );
                }
            }

            if ( this.scholarshipRepository.findAll ()
                    .isEmpty () )
            {
                this.scholarshipRepository.save ( new Scholarship ( 1L, ScholarshipTypes.SOCIAL, 2, new BigDecimal ( 3000.00 ), new BigDecimal ( 0.00 ), 0.0d, 3L, new Date ( 119, 1, 12 ), null, Statuses.AWAITING, ( Student ) this.accountRepository.findByMail ( "student@student.pl" )
                        .getPerson () ) );
                this.scholarshipRepository.save ( new Scholarship ( 2L, ScholarshipTypes.SOCIAL, 1, new BigDecimal ( 1000.00 ), new BigDecimal ( 500.00 ), 0.0d, 2L, new Date ( 119, 1, 13 ), new Date ( 119, 1, 14 ), Statuses.ACCEPTED, ( Student ) this.accountRepository.findByMail ( "student2@student2.pl" )
                        .getPerson () ) );
            }
        };
    }
}