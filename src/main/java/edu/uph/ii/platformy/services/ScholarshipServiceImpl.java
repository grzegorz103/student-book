package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.ScholarshipRepository;
import edu.uph.ii.platformy.repositories.UtilsRepository;
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
    private final ScholarshipRepository scholarshipRepository;
    private final AccountRepository accountRepository;
    private final SemestralGradeService semestralGradeService;
    private final UtilsRepository utilsRepository;

    @Autowired
    public ScholarshipServiceImpl ( ScholarshipRepository scholarshipRepository, AccountRepository accountRepository, SemestralGradeService semestralGradeService, UtilsRepository utilsRepository )
    {
        this.scholarshipRepository = scholarshipRepository;
        this.accountRepository = accountRepository;
        this.semestralGradeService = semestralGradeService;
        this.utilsRepository = utilsRepository;
    }

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
                return this.scholarshipRepository.findAllForDean ( pageable );
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

        return !scholarship.getStudent ()
                .getId ()
                .equals ( myUser.getPerson ()
                        .getId () );
    }

    @Override
    public boolean hasStudentAwaitingScholarship ( ScholarshipTypes scholarshipType )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () != Role.UserTypes.ROLE_STUDENT )
            {
                return true;
            }
        }

        if ( scholarshipType == ScholarshipTypes.SCIENTIFIC && ( ( Student ) myUser.getPerson () ).getSemester () <= 1 )
        {
            return true;
        }

        return ( !this.scholarshipRepository.findAwaitingsByStudentAndScholarshipType ( myUser.getPerson ()
                .getId (), ( ( Student ) myUser.getPerson () ).getSemester (), scholarshipType )
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
        scholarship.setSemester ( ( ( Student ) myUser.getPerson () ).getSemester () );
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

        double avg;

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

    @Override
    public void openOrClose ()
    {
        Utils status = utilsRepository.getOne ( 1L );

        status.setScholarshipEnabled ( !status.getScholarshipEnabled () );

        utilsRepository.save ( status );
    }

    @Override
    public boolean isOpen ()
    {
        return utilsRepository.getOne ( 1L )
                .getScholarshipEnabled ();
    }
}
