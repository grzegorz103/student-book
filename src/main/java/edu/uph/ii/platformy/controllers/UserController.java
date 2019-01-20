package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.CourseService;
import edu.uph.ii.platformy.services.UserService;
import edu.uph.ii.platformy.services.declarations.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes ( "user" )
public class UserController
{
    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public UserController ( UserService userService, CourseService courseService )
    {
        this.userService = userService;
        this.courseService = courseService;
    }


    @ModelAttribute ( "user" )
    public User user ()
    {
        User u = new User ();
        u.setPerson ( new Student () );
        return u;
    }


    @GetMapping ( "/register" )
    public String registerForm ( Model model, @ModelAttribute ( "user" ) User u )
    {
        model.addAttribute ( "user", u );
        return "registerForm";
    }


    @PostMapping ( "/register" )
    public String registerUser ( @ModelAttribute ( "user" ) User user, Model model, SessionStatus sessionStatus )
    {
        userService.save ( user );
        sessionStatus.setComplete ();
        return "redirect:/";
    }

    @ModelAttribute ( "coursesList" )
    public List< Course > getCourseList ()
    {
        return this.courseService.getAllCourses();
    }
}
