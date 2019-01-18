package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Course;
import edu.uph.ii.platformy.models.CourseChange;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.services.CourseChangeService;
import edu.uph.ii.platformy.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize ( "isAuthenticated()" )
@RequestMapping ( "/courseChange" )
public class CourseChangeController
{
    private final CourseChangeService courseChangeService;
    private final CourseService courseService;

    @Autowired
    public CourseChangeController ( CourseChangeService courseChangeService, CourseService courseService )
    {
        this.courseChangeService = courseChangeService;
        this.courseService = courseService;
    }

    @RequestMapping ( "/list" )
    public String showCourseChangeList ( Model model, @RequestParam ( name = "notEnoughtCourses", required = false ) Boolean notEnoughtCourses, @RequestParam ( name = "hasAwaiting", required = false ) Boolean hasAwaiting, Pageable pageable )
    {
        Page page = this.courseChangeService.findCourseChange ( pageable );

        model.addAttribute ( "notEnoughtCourses", notEnoughtCourses );
        model.addAttribute ( "hasAwaiting", hasAwaiting );
        model.addAttribute ( "hasAwaitingCourseChange", this.courseChangeService.hasStudentAwaitingCourseChange () );
        Course actualCourse = this.courseService.getActualStudentCourse ();
        if ( actualCourse != null )
        {
            model.addAttribute ( "actualCourse", actualCourse
                    .getName () );
        }
        model.addAttribute ( "courseChangeList", page );

        return "courseChangeList";
    }

    @Secured ( "ROLE_STUDENT" )
    @GetMapping ( { "/add", "/edit/{id}" } )
    public String addCourseChangeForm ( Model model, @PathVariable ( name = "id", required = false ) CourseChange courseChange )
    {
        if ( courseChange == null )
        {
            courseChange = new CourseChange ();
        }
        else if ( courseChange.getStatus () != Statuses.REJECTED || !this.courseChangeService.courseChangeBelongsToLoggedStudent ( courseChange ) )
        {
            return "redirect:/courseChange/list";
        }

        if ( this.courseChangeService.hasStudentAwaitingCourseChange () )
        {
            return "redirect:/courseChange/list?hasAwaiting=true";
        }

        List< Course > availableCourses = this.courseService.findAvailableCourses ();

        if ( availableCourses.isEmpty () )
        {
            return "redirect:/courseChange/list?notEnoughtCourses=true";
        }

        model.addAttribute ( "availableCourses", availableCourses );
        model.addAttribute ( "courseChange", courseChange );

        return "courseChangeForm";
    }

    @Secured ( "ROLE_STUDENT" )
    @PostMapping ( "/save" )
    public String addCourseChange ( @Valid @ModelAttribute ( "courseChange" ) CourseChange courseChange, BindingResult bindingResult )
    {
        if ( this.courseChangeService.hasStudentAwaitingCourseChange () )
        {
            return "redirect:/courseChange/list?hasAwaiting=true";
        }

        this.courseChangeService.save ( courseChange );

        return "redirect:/courseChange/list";
    }

    @Secured ( "ROLE_DEAN" )
    @GetMapping ( "/list/more/{id}" )
    public String acceptCourseChangeForm ( Model model, @PathVariable ( "id" ) CourseChange courseChange )
    {
        if ( courseChange == null || courseChange.getStatus () != Statuses.AWAITING )
        {
            return "redirect:/courseChange/list";
        }

        model.addAttribute ( "courseChange", courseChange );

        return "acceptOrRejectCourseChangeForm";
    }

    @Secured ( "ROLE_DEAN" )
    @GetMapping ( "/list/accept/{id}" )
    public String acceptCourseChange ( @PathVariable ( "id" ) CourseChange courseChange )
    {
        this.courseChangeService.acceptCourseChange ( courseChange );

        return "redirect:/courseChange/list";
    }

    @Secured ( "ROLE_DEAN" )
    @PostMapping ( "/list/reject" )
    public String rejectCourseChange ( @Valid @ModelAttribute ( "courseChange" ) CourseChange courseChange, BindingResult bindingResult )
    {
        this.courseChangeService.rejectCourseChange ( courseChange );

        return "redirect:/courseChange/list";
    }
}
