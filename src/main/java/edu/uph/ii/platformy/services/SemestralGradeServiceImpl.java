package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Grade;
import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.repositories.SemestralGradesRepository;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("semestralGradeService")
public class SemestralGradeServiceImpl implements SemestralGradeService
{
        private final SemestralGradesRepository semestralGradesRepository;

        @Autowired
        public SemestralGradeServiceImpl ( SemestralGradesRepository semestralGradesRepository )
        {
                this.semestralGradesRepository = semestralGradesRepository;
        }

        @Override
        public List<SemestralGrade> getSemestralGradesBySubject ( Subject subject )
        {
                return semestralGradesRepository.findAllBySubject( subject );
        }

        @Override
        public List<SemestralGrade> getSemestralGradesByStudentAndSemester ( Student student )
        {
                return this.semestralGradesRepository.findSemestralGradesByStudentAndSemester( student.getId(), (student.getSemester() > 1) ? (student.getSemester() - 1) : student.getSemester() );
        }

        @Override
        public List<SemestralGrade> getSemestralGradesByStudentForCondition ( Student student )
        {
                return this.semestralGradesRepository.findSemestralGradesByStudentForCondition( student.getId(), student.getSemester(), student.getCourse()
                        .getId() );
        }

        @Override
        public void changeGrades ( SemestralGrade grade )
        {
                semestralGradesRepository.save( grade );
        }
}
