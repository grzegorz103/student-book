package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.CourseRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ( "courseService" )
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List< Course > findAvailableCourses ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();
        User myUser = accountRepository.findByMail ( user.getUsername () );

        Course course = ( ( Student ) myUser.getPerson () ).getCourse ();

        if ( course == null )
        {
            return this.courseRepository.findAvailableCourses ( 0L );
        }

        return this.courseRepository.findAvailableCourses ( course
                .getId () );
    }

    @Override
    public Course getActualStudentCourse ()
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () != Role.UserTypes.ROLE_STUDENT )
            {
                return null;
            }
        }

        return this.studentRepository.getOne ( myUser.getPerson ()
                .getId () )
                .getCourse ();
    }
}
