package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Attendance;
import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.repositories.AttendanceRepository;
import edu.uph.ii.platformy.repositories.LessonRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import edu.uph.ii.platformy.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                Attendance atd = new Attendance();

                for (Student stud : students) {
                       if (stud.getSemester().equals(sub.getSemester()))
                       {
                                atd.setId(null);
                                atd.setPresence(false);
                                atd.setStudent(stud);
                                atd.setLesson(les);
                                attendanceRepository.save(atd);
                       }
                }
        }
}