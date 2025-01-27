package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.UnitRepository;
import edu.uph.ii.platformy.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service ( "workshopService" )
public class WorkshopServiceImpl implements WorkshopService
{
    private final UnitRepository unitRepository;

    private final WorkshopRepository workshopRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public WorkshopServiceImpl(UnitRepository unitRepository, WorkshopRepository workshopRepository, AccountRepository accountRepository )
    {
        this.unitRepository = unitRepository;
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
            if ( role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                Instructor ins = ( Instructor ) u.getPerson ();
                return workshopRepository.findAllByInstructor ( ins );
            }
            else return workshopRepository.findAll ();
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
            if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return ( Student ) u.getPerson ();
            }
            else return new Student ();
        }
            return new Student ();
    }

    @Override
    public Instructor findActiveInstructor() {

        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();
        edu.uph.ii.platformy.models.User u = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : u.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_INSTRUCTOR )
            {
                return ( Instructor ) u.getPerson ();
            }
            else return new Instructor ();

        }
        return new Instructor ();
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

    @Override
    public void addWorkshop(Workshop workshop, Instructor instructor) {

        workshop.setInstructor(instructor);
        workshopRepository.save(workshop);
    }

    @Override
    public void deleteWorkshop(Workshop workshop) {
        workshopRepository.delete(workshop);
    }

    @Override
    public void addUnit(Unit unit, Workshop workshop) {

        unit.setWorkshop(workshop);

        Set <Unit> units = workshop.getUnits();
        units.add(unit);
        workshop.setUnits(units);

        workshopRepository.save(workshop);


    }

    @Override
    public void deleteUnit(Unit unit) {
        unitRepository.delete(unit);
    }


}
