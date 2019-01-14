package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service ("subjectService")
public class SubjectServiceImpl implements SubjectService {
        @Autowired
        private SubjectRepository subjectRepository;

        @Autowired
        private LessonRepository lessonRepository;

        @Autowired
        private AttendanceRepository attendanceRepository;

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private AccountRepository accountRepository;

        @Override
        public List<Subject> findAll() {
                return subjectRepository.findAll();
        }

        @Override
        public List<Lesson> getLessons(Subject subject) {

                return lessonRepository.findAllBySubject(subject);
        }

        @Override
        public List<Attendance> getAttendances(Lesson lesson) {

                return attendanceRepository.findAllByLesson(lesson);
        }

        @Override
        public void changeStudentAttendance(Long atd, Long sts) {
                Attendance attendance = attendanceRepository.findById(atd).get();
                attendance.setPresence(sts != 1);
                attendanceRepository.save(attendance);
        }

        @Override
        public void addLesson(Lesson lesson, Subject subject) {
                lesson.setId(null);
                lesson.setSubject(subject);
                lessonRepository.save(lesson);
        }

        @Override
        public void addAttendanceList(Subject sub, Lesson les) {
                List <Student> students = studentRepository.findAllByCourse(sub.getCourse());


                for (Student stud : students) {
                       if (stud.getSemester().equals(sub.getSemester()))
                       {
                                Attendance atd = new Attendance();
                                atd.setPresence(false);
                                atd.setStudent(stud);
                                atd.setLesson(les);
                                attendanceRepository.save(atd);
                       }
                }
        }

        @Override
        public List<Subject> findSubjects() {

                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );

                for (Role role : u.getRoles()) {
                        if (role.getUserType() == Role.UserTypes.ROLE_ADMIN )
                                {
                                        return subjectRepository.findAll();
                                }
                        else if (role.getUserType() == Role.UserTypes.ROLE_INSTRUCTOR)
                                {
                                        Instructor ins = ( Instructor ) u.getPerson();
                                        return subjectRepository.findAllByInstructor(ins);
                                }
                        else if (role.getUserType() == Role.UserTypes.ROLE_USER)
                                {
                                        Student std = ( Student ) u.getPerson();
                                        List<Subject> sbjs = new ArrayList<>();

                                        for (Subject sbj : subjectRepository.findAllByCourse(std.getCourse())) {
                                                if (sbj.getSemester() <= std.getSemester()) sbjs.add(sbj);
                                        } return sbjs;
                                }
                }

                return subjectRepository.findAll();
        }

}