package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.SemestralGradesRepository;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service ( "semestralGradeService" )
public class SemestralGradeServiceImpl implements SemestralGradeService
{
    private final SemestralGradesRepository semestralGradesRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public SemestralGradeServiceImpl ( SemestralGradesRepository semestralGradesRepository, AccountRepository accountRepository )
    {
        this.semestralGradesRepository = semestralGradesRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List< SemestralGrade > getSemestralGradesBySubject ( Subject subject )
    {
        return semestralGradesRepository.findAllBySubject ( subject );
    }

    @Override
    public List< SemestralGrade > getSemestralGradesByStudentAndSemester ( Student student )
    {
        return this.semestralGradesRepository.findSemestralGradesByStudentAndSemester ( student.getId (), ( student.getSemester () > 1 ) ? ( student.getSemester () - 1 ) : student.getSemester () );
    }

    @Override
    public List< SemestralGrade > getSemestralGradesByStudentForCondition ( Student student )
    {
        return this.semestralGradesRepository.findSemestralGradesByStudentForCondition ( student.getId (), student.getSemester (), student.getCourse ()
                .getId () );
    }

    @Override
    public void changeGrades ( SemestralGrade grade )
    {
        semestralGradesRepository.save ( grade );
    }

    @Override
    public List< SemestralGrade > getSemestralGradesByStudent ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        return this.semestralGradesRepository.findAllByStudentOrdered ( myUser.getPerson ()
                .getId () );
    }

    @Override
    public Double getActualAvgGradesForStudent ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        List< SemestralGrade > semestralGrades = this.semestralGradesRepository.findSemestralGradesByStudentAndSemester ( myUser.getPerson ()
                .getId (), ( ( Student ) myUser.getPerson () )
                .getSemester () );

        double sum = 0;
        int count = 0;

        for ( SemestralGrade semestralGrade : semestralGrades )
        {
            if ( semestralGrade.getTotalGrade () != null )
            {
                sum += semestralGrade.getTotalGrade ();
                ++count;
            }
            else if ( semestralGrade.getSecondTerminGrade () != null )
            {
                sum += semestralGrade.getSecondTerminGrade ();
                ++count;
            }
            else if ( semestralGrade.getFirstTerminGrade () != null )
            {
                sum += semestralGrade.getFirstTerminGrade ();
                ++count;
            }
        }

        double avg = 0;
        if ( count != 0 )
        {
            avg = new BigDecimal ( sum / count ).setScale ( 2, RoundingMode.HALF_UP )
                    .doubleValue ();
        }
        return avg;
    }
}
