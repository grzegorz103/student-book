package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Attendance;
import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.repositories.AttendanceRepository;
import edu.uph.ii.platformy.repositories.LessonRepository;
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
                attendance.setPresence((sts == 1) ? false : true);
                attendanceRepository.save(attendance);
        }
}