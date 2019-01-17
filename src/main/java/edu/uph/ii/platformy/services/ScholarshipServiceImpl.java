package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.ScholarshipRepository;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service ( "scholarshipService" )
public class ScholarshipServiceImpl implements ScholarshipService
{
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SemestralGradeService semestralGradeService;

    @Override
    public Page< Scholarship > findScholarships ( Pageable pageable )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return this.scholarshipRepository.findAllByStudent ( myUser.getPerson ()
                        .getId (), pageable );
            }

            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN )
            {
                return this.scholarshipRepository.findAllOrdered ( pageable );
            }
        }
        return null;
    }

    @Override
    public void acceptScholarship ( Scholarship scholarship )
    {
        BigDecimal amount = scholarship.getAmount ();

        scholarship = this.scholarshipRepository.findById ( scholarship.getId () )
                .orElse ( null );

        if ( scholarship != null )
        {
            scholarship.setStatus ( Statuses.ACCEPTED );
            scholarship.setStatusChangeDate ( new Date () );
            scholarship.setAmount ( amount );

            this.scholarshipRepository.save ( scholarship );
        }
    }

    @Override
    public void rejectScholarship ( Scholarship scholarship )
    {
        if ( scholarship != null && scholarship.getStatus () == Statuses.AWAITING )
        {
            scholarship.setStatus ( Statuses.REJECTED );
            scholarship.setStatusChangeDate ( new Date () );
            scholarship.setAmount ( new BigDecimal ( 0.00 ) );

            this.scholarshipRepository.save ( scholarship );
        }
    }

    @Override
    public boolean scholarshipBelongsToLoggedStudent ( Scholarship scholarship )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        return scholarship.getStudent ()
                .getId ()
                .equals ( myUser.getPerson ()
                        .getId () );
    }

    @Override
    public boolean hasStudentAwaitingScholarship ( ScholarshipTypes scholarshipTypes )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        return ( !this.scholarshipRepository.findAwaitingsByStudentAndScholarshipType ( myUser.getPerson ()
                .getId (), scholarshipTypes )
                .isEmpty () );
    }

    @Override
    public void save ( Scholarship scholarship )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        scholarship.setStudent ( ( Student ) myUser.getPerson () );
        scholarship.setSubmittingDate ( new Date () );
        scholarship.setStatus ( Statuses.AWAITING );
        scholarship.setId ( null );

        this.scholarshipRepository.save ( scholarship );
    }

    @Override
    public Double getPreviousSemesterStudentsMarks ( Student student )
    {
        double sum = 0;
        int count = 0;

        List< SemestralGrade > semestralGrades = this.semestralGradeService.getSemestralGradesByStudentAndSemester ( student );

        for ( SemestralGrade semestralGrade : semestralGrades )
        {
            if ( semestralGrade.getTotalGrade () == null )
            {
                continue;
            }

            sum += semestralGrade.getTotalGrade ();
            ++count;
        }

        Double avg;

        if ( count == 0 )
        {
            avg = 2.0d;
        }
        else
        {
            BigDecimal tmp = new BigDecimal ( sum / count );
            avg = tmp.setScale ( 2, RoundingMode.HALF_UP )
                    .doubleValue ();
        }
        return avg;
    }
}
