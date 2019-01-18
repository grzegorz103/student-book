package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.ConditionRepository;
import edu.uph.ii.platformy.repositories.UtilsRepository;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service ( "conditionService" )
public class ConditionServiceImpl implements ConditionService
{
    private final ConditionRepository conditionRepository;
    private final AccountRepository accountRepository;
    private final SemestralGradeService semestralGradeService;
    private final UtilsRepository utilsRepository;

    @Autowired
    public ConditionServiceImpl ( ConditionRepository conditionRepository, AccountRepository accountRepository, SemestralGradeService semestralGradeService, UtilsRepository utilsRepository )
    {
        this.conditionRepository = conditionRepository;
        this.accountRepository = accountRepository;
        this.semestralGradeService = semestralGradeService;
        this.utilsRepository = utilsRepository;
    }

    @Override
    public Page< Condition > findConditions ( Pageable pageable )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return this.conditionRepository.findAllByStudent ( myUser.getPerson ()
                        .getId (), pageable );
            }

            if ( role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                return this.conditionRepository.findAllByInstructor ( myUser.getPerson ()
                        .getId (), pageable );
            }
        }
        return null;
    }

    @Override
    public boolean canStudentGetCondition ()
    {
        return ( !getStudentSubjectsForCondition ().isEmpty () );
    }

    @Override
    public List< Subject > getStudentSubjectsForCondition ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () != Role.UserTypes.ROLE_STUDENT )
            {
                return new ArrayList<> ();
            }
        }
        List< Subject > subjectConditionList = this.conditionRepository.findAllByStudent ( myUser.getPerson ()
                .getId () )
                .stream ()
                .map ( Condition::getSubject )
                .collect ( Collectors.toList () );

        List< Subject > subjectLowGradeList = this.semestralGradeService.getSemestralGradesByStudentForCondition ( ( Student ) myUser.getPerson () )
                .stream ()
                .map ( SemestralGrade::getSubject )
                .collect ( Collectors.toList () );

        subjectLowGradeList.removeAll ( subjectConditionList );

        return ( subjectLowGradeList );
    }

    @Override
    public void acceptCondition ( Condition condition )
    {
        condition = this.conditionRepository.findById ( condition.getId () )
                .orElse ( null );

        if ( condition != null )
        {
            condition.setStatus ( Statuses.ACCEPTED );
            condition.setStatusChangeDate ( new Date () );

            this.conditionRepository.save ( condition );
        }
    }

    @Override
    public void rejectCondition ( Condition condition )
    {
        String conditionRejectionText = condition.getConditionRejectionJustification ()
                .trim ();

        condition = this.conditionRepository.findById ( condition.getId () )
                .orElse ( null );

        if ( condition != null && condition.getStatus () == Statuses.AWAITING )
        {
            condition.setStatus ( Statuses.REJECTED );
            condition.setStatusChangeDate ( new Date () );
            condition.setConditionRejectionJustification ( conditionRejectionText );

            this.conditionRepository.save ( condition );
        }
    }

    @Override
    public boolean conditionBelongsToLoggedStudent ( Condition condition )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        Student student = ( Student ) accountRepository.findByMail ( user.getUsername () )
                .getPerson ();

        return condition.getStudent ()
                .getId ()
                .equals ( student.getId () );
    }

    @Override
    public void save ( Condition condition )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        if ( condition.getId () != null )
        {
            this.conditionRepository.findById ( condition.getId () )
                    .ifPresent ( tmp -> condition.setConditionRejectionJustification ( tmp.getConditionRejectionJustification ()
                            .trim () ) );
        }

        condition.setStudent ( ( Student ) myUser.getPerson () );
        condition.setSubmittingDate ( new Date () );
        condition.setStatus ( Statuses.AWAITING );
        condition.setId ( null );

        this.conditionRepository.save ( condition );
    }

    @Override
    public void openOrClose ()
    {
        Utils status = utilsRepository.getOne ( 1L );

        status.setConditionEnabled ( !status.getConditionEnabled () );

        utilsRepository.save ( status );
    }

    @Override
    public boolean isOpen ()
    {
        return utilsRepository.getOne ( 1L )
                .getConditionEnabled ();
    }
}
