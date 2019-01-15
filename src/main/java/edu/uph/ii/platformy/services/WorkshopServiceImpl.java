package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Workshop;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service ( "workshopService" )
public class WorkshopServiceImpl implements WorkshopService
{
    private final InstructorRepository instructorRepository;

    private final WorkshopRepository workshopRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public WorkshopServiceImpl(InstructorRepository instructorRepository, WorkshopRepository workshopRepository, AccountRepository accountRepository )
    {
        this.instructorRepository = instructorRepository;
        this.workshopRepository = workshopRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Workshop> findWorkshopsByInstructor(Instructor instructor)
    {
        return workshopRepository.findAllByInstructor(instructor);
    }

    @Override
    public List<Workshop> findAll() {
        return workshopRepository.findAll();
    }

    @Override
    public List<Workshop> findWorkshopList() {

        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();
        edu.uph.ii.platformy.models.User u = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : u.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN )
            {
                return workshopRepository.findAll ();
            }
            else if ( role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                Instructor ins = ( Instructor ) u.getPerson ();
                return workshopRepository.findAllByInstructor ( ins );
            }
            else if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return workshopRepository.findAll ();
            }
        }

        return workshopRepository.findAll ();
    }

    @Override
    public Student findActiveStudent() {

        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();
        edu.uph.ii.platformy.models.User u = accountRepository.findByMail ( user.getUsername () );

            for ( Role role : u.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN )
            {
                return new Student ();
            }
            else if ( role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                return new Student ();
            }
            else if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return ( Student ) u.getPerson ();
            }
        }
            return new Student ();
    }

    @Override
    public void deleteStudentFromWorkshop(Student student, Workshop workshop) {

        if (workshop.getStudents().contains(student)){
            workshop.getStudents().remove(student);
            workshopRepository.save(workshop);
        }
    }

    @Override
    public void addStudentToWorkshop(Student student, Workshop workshop) {

        workshop.getStudents().add(student);
        workshopRepository.save(workshop);

    }


}
