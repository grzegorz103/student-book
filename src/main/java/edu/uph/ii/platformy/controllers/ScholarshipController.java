package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Scholarship;
import edu.uph.ii.platformy.models.ScholarshipTypes;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.services.ScholarshipService;
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

@Controller
@PreAuthorize ( "isAuthenticated()" )
@RequestMapping ( "/scholarships" )
public class ScholarshipController
{
    @Autowired
    private ScholarshipService scholarshipService;

    @RequestMapping ( "/list" )
    public String showScholarshipList ( Model model, Pageable pageable )
    {
        Page page = this.scholarshipService.findScholarships ( pageable );

        model.addAttribute ( "hasAwaitingSocial", this.scholarshipService.hasStudentAwaitingScholarship ( ScholarshipTypes.SOCIAL ) );
        model.addAttribute ( "hasAwaitingScientific", this.scholarshipService.hasStudentAwaitingScholarship ( ScholarshipTypes.SCIENTIFIC ) );
        model.addAttribute ( "scholarshipList", page );

        return "scholarshipList";
    }

    @Secured ( "ROLE_STUDENT" )
    @GetMapping ( { "/add/social", "/edit/social/{id}" } )
    public String addSocialScholarshipForm ( Model model, @PathVariable ( name = "id", required = false ) Scholarship scholarship )
    {
        if ( scholarship == null )
        {
            scholarship = new Scholarship ();
            scholarship.setScholarshipType ( ScholarshipTypes.SOCIAL );
            scholarship.setPeopleNumber ( null );
        }
        else if ( scholarship.getStatus () != Statuses.REJECTED || scholarship.getScholarshipType () != ScholarshipTypes.SOCIAL || !this.scholarshipService.scholarshipBelongsToLoggedStudent ( scholarship ) )
        {
            return "redirect:/scholarships/list";
        }

        if ( this.scholarshipService.hasStudentAwaitingScholarship ( ScholarshipTypes.SOCIAL ) )
        {
            return "redirect:/scholarships/list";
        }

        model.addAttribute ( "scholarship", scholarship );

        return "scholarshipForm";
    }

    @Secured ( "ROLE_STUDENT" )
    @GetMapping ( { "/add/scientific", "/edit/scientific/{id}" } )
    public String addScientificScholarshipForm ( Model model, @PathVariable ( name = "id", required = false ) Scholarship scholarship )
    {
        if ( scholarship == null )
        {
            scholarship = new Scholarship ();
            scholarship.setScholarshipType ( ScholarshipTypes.SCIENTIFIC );
        }
        else if ( scholarship.getStatus () != Statuses.REJECTED || scholarship.getScholarshipType () != ScholarshipTypes.SCIENTIFIC || !this.scholarshipService.scholarshipBelongsToLoggedStudent ( scholarship ) )
        {

            return "redirect:/scholarships/list";
        }

        if ( this.scholarshipService.hasStudentAwaitingScholarship ( ScholarshipTypes.SCIENTIFIC ) )
        {
            return "redirect:/scholarships/list";
        }

        model.addAttribute ( "scholarship", scholarship );

        return "scholarshipForm";
    }

    @Secured ( "ROLE_STUDENT" )
    @PostMapping ( "/save" )
    public String addScholarship ( @Valid @ModelAttribute ( "scholarship" ) Scholarship scholarship, BindingResult bindingResult )
    {
        this.scholarshipService.save ( scholarship );

        return "redirect:/scholarships/list";
    }

    @Secured ( "ROLE_DEAN" )
    @GetMapping ( "/list/more/{id}" )
    public String acceptScholarshipForm ( Model model, @PathVariable ( "id" ) Scholarship scholarship )
    {
        if ( scholarship == null || scholarship.getStatus () != Statuses.AWAITING )
        {
            return "redirect:/scholarships/list";
        }

        scholarship.setAmount ( null );
        model.addAttribute ( "avgGrade", this.scholarshipService.getPreviousSemesterStudentsMarks ( scholarship.getStudent () ) );
        model.addAttribute ( "scholarship", scholarship );

        return "acceptOrRejectScholarshipForm";
    }

    @Secured ( "ROLE_DEAN" )
    @PostMapping ( "/list/accept" )
    public String acceptScholarship ( @Valid @ModelAttribute ( "scholarship" ) Scholarship scholarship, BindingResult bindingResult )
    {
        this.scholarshipService.acceptScholarship ( scholarship );

        return "redirect:/scholarships/list";
    }

    @Secured ( "ROLE_DEAN" )
    @GetMapping ( "/list/reject/{id}" )
    public String rejectScholarship ( @PathVariable ( "id" ) Scholarship scholarship )
    {
        this.scholarshipService.rejectScholarship ( scholarship );

        return "redirect:/scholarships/list";
    }
}
