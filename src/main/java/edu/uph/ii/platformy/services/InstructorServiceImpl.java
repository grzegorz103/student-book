package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import edu.uph.ii.platformy.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service ( "instructorService" )
public class InstructorServiceImpl implements InstructorService
{
    private final InstructorRepository instructorRepository;

    private final OpinionRepository opinionRepository;

    private final AccountRepository accountRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    public InstructorServiceImpl ( InstructorRepository instructorRepository, OpinionRepository opinionRepository, AccountRepository accountRepository, SubjectRepository subjectRepository)
    {
        this.instructorRepository = instructorRepository;
        this.opinionRepository = opinionRepository;
        this.accountRepository = accountRepository;
        this.subjectRepository = subjectRepository;

    }

    @Override
    public List< Opinion > getOpinions ( Instructor instructor )
    {
        return opinionRepository.findAllByInstructor ( instructor );
    }

    @Override
    public void addOpinion ( Opinion opinion, Instructor instructor )
    {

        opinion.setId ( null );
        opinion.setStatus ( Statuses.AWAITING );
        opinion.setInstructor ( instructor );

        opinionRepository.save ( opinion );
    }

    @Override
    public List< Instructor > findAll ()
    {
        return instructorRepository.findAll ();
    }

    @Override
    public void changeOpinionStatus ( Long opn, Long sta )
    {

        Opinion opinion = opinionRepository.findById ( opn )
                .get ();
        opinion.setStatus ( ( sta == 1 ) ? Statuses.ACCEPTED : Statuses.REJECTED );
        opinionRepository.save ( opinion );
    }

    @Override
    public List< Subject > findSubjectsByInstructor ( Instructor instructor )
    {
        return subjectRepository.findAllByInstructor ( instructor );
    }

    @Override
    public List< Instructor > findInstructors ()
    {

        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();
        edu.uph.ii.platformy.models.User u = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : u.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN || role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                return instructorRepository.findAll ();
            }
            else if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                Student std = ( Student ) u.getPerson ();
                List< Instructor > ins = new ArrayList<> ();

                for ( Subject sbj : subjectRepository.findAllByCourse ( std.getCourse () ) )
                {

                    if ( !ins.contains ( sbj.getInstructor () ) && sbj.getSemester () <= std.getSemester () )
                        ins.add ( sbj.getInstructor () );
                }
                return ins;
            }
        }

        return instructorRepository.findAll ();
    }


}
