package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.CourseChangeRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import edu.uph.ii.platformy.repositories.UtilsRepository;
import edu.uph.ii.platformy.services.declarations.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service ( "changeCourseService" )
public class CourseChangeServiceImpl implements CourseChangeService
{
    private final AccountRepository accountRepository;
    private final CourseChangeRepository courseChangeRepository;
    private final StudentRepository studentRepository;
    private final UtilsRepository utilsRepository;
    private final SpecializationService specializationService;

    @Autowired
    public CourseChangeServiceImpl ( AccountRepository accountRepository, CourseChangeRepository courseChangeRepository, StudentRepository studentRepository, UtilsRepository utilsRepository, SpecializationService specializationService )
    {
        this.accountRepository = accountRepository;
        this.courseChangeRepository = courseChangeRepository;
        this.studentRepository = studentRepository;
        this.utilsRepository = utilsRepository;
        this.specializationService = specializationService;
    }

    @Override
    public Page< CourseChange > findCourseChange ( Pageable pageable )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return this.courseChangeRepository.findAllByStudent ( myUser.getPerson ()
                        .getId (), pageable );
            }

            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN )
            {
                return this.courseChangeRepository.findAllCourseChange ( pageable );
            }
        }
        return null;
    }

    @Override
    public boolean hasStudentAwaitingCourseChange ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        return ( !this.courseChangeRepository.findAwaitingsByStudent ( myUser.getPerson ()
                .getId () )
                .isEmpty () );
    }

    @Override
    public boolean courseChangeBelongsToLoggedStudent ( CourseChange courseChange )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        return courseChange.getStudent ()
                .getId ()
                .equals ( accountRepository.findByMail ( user.getUsername () )
                        .getPerson ()
                        .getId () );
    }

    @Override
    public void save ( CourseChange courseChange )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        if ( courseChange.getId () != null )
        {
            this.courseChangeRepository.findById ( courseChange.getId () )
                    .ifPresent ( tmp -> courseChange.setCourseRejectionJustification ( tmp.getCourseRejectionJustification ()
                            .trim () ) );
        }

        courseChange.setStudent ( ( Student ) myUser.getPerson () );
        courseChange.setSubmittingDate ( new Date () );
        courseChange.setStatus ( Statuses.AWAITING );
        courseChange.setId ( null );

        this.courseChangeRepository.save ( courseChange );

    }

    @Override
    public void acceptCourseChange ( CourseChange courseChange )
    {
        courseChange = this.courseChangeRepository.findById ( courseChange.getId () )
                .orElse ( null );

        if ( courseChange != null )
        {
            courseChange.setStatus ( Statuses.ACCEPTED );
            courseChange.setStatusChangeDate ( new Date () );

            courseChange.getStudent ()
                    .setCourse ( courseChange.getNewCourse () );

            courseChange.getStudent ()
                    .setSpecChosen ( false );

            courseChange.getStudent ()
                    .setSpecialization ( null );

            if ( !utilsRepository.getOne ( 1L )
                    .getSpecialization_enabled () )
            {
                if ( !courseChange.getStudent ()
                        .getSpecChosen () )
                {
                    courseChange.getStudent ()
                            .setSpecialization ( this.specializationService.getSpecialization ( courseChange.getStudent ()
                                    .getCourse () ) );
                    courseChange.getStudent ()
                            .setSpecChosen ( true );
                }
            }

            this.studentRepository.save ( courseChange.getStudent () );
            this.courseChangeRepository.save ( courseChange );
        }
    }

    @Override
    public void rejectCourseChange ( CourseChange courseChange )
    {
        String courseRejectionText = courseChange.getCourseRejectionJustification ()
                .trim ();

        courseChange = this.courseChangeRepository.findById ( courseChange.getId () )
                .orElse ( null );

        if ( courseChange != null && courseChange.getStatus () == Statuses.AWAITING )
        {
            courseChange.setStatus ( Statuses.REJECTED );
            courseChange.setStatusChangeDate ( new Date () );
            courseChange.setCourseRejectionJustification ( courseRejectionText );

            this.courseChangeRepository.save ( courseChange );
        }
    }
}
