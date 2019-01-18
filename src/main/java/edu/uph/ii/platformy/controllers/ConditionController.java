package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Condition;
import edu.uph.ii.platformy.models.ConditionTypes;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.services.ConditionService;
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
import java.util.Arrays;
import java.util.List;

@Controller
@PreAuthorize ( "isAuthenticated()" )
@RequestMapping ( "/conditions" )
public class ConditionController
{
    private final ConditionService conditionService;

    @Autowired
    public ConditionController ( ConditionService conditionService )
    {
        this.conditionService = conditionService;
    }

    @RequestMapping ( "/list" )
    public String showConditionList ( Model model, Pageable pageable )
    {
        Page page = this.conditionService.findConditions ( pageable );

        model.addAttribute ( "canStudentGetCondition", this.conditionService.canStudentGetCondition () );
        model.addAttribute ( "isOpen", this.conditionService.isOpen () );
        model.addAttribute ( "conditionList", page );

        return "conditionList";
    }

    @Secured ( "ROLE_STUDENT" )
    @GetMapping ( { "/add", "/edit/{id}" } )
    public String addConditionForm ( Model model, @PathVariable ( name = "id", required = false ) Condition condition )
    {
        if ( condition == null )
        {
            condition = new Condition ();
        }
        else if ( condition.getStatus () != Statuses.REJECTED || !this.conditionService.conditionBelongsToLoggedStudent ( condition ) )
        {
            return "redirect:/conditions/list";
        }

        List< Subject > availableSubjects = this.conditionService.getStudentSubjectsForCondition ();

        if ( availableSubjects.isEmpty () )
        {
            return "redirect:/conditions/list";
        }

        model.addAttribute ( "availableSubjects", availableSubjects );
        model.addAttribute ( "conditionTypes", Arrays.asList ( ConditionTypes.SHORT, ConditionTypes.LONG ) );

        if ( !this.conditionService.canStudentGetCondition () || !this.conditionService.isOpen () )
        {
            return "redirect:/conditions/list";
        }

        model.addAttribute ( "condition", condition );

        return "conditionForm";
    }

    @Secured ( "ROLE_STUDENT" )
    @PostMapping ( "/save" )
    public String addCondition ( @Valid @ModelAttribute ( "condition" ) Condition condition, BindingResult bindingResult )
    {
        if ( !this.conditionService.isOpen () )
        {
            return "redirect:/conditions/list";
        }

        this.conditionService.save ( condition );

        return "redirect:/conditions/list";
    }

    @Secured ( "ROLE_INSTRUCTOR" )
    @GetMapping ( "/list/more/{id}" )
    public String acceptConditionForm ( Model model, @PathVariable ( "id" ) Condition condition )
    {
        if ( condition == null || condition.getStatus () != Statuses.AWAITING )
        {
            return "redirect:/conditions/list";
        }

        model.addAttribute ( "condition", condition );

        return "acceptOrRejectConditionForm";
    }

    @Secured ( "ROLE_INSTRUCTOR" )
    @GetMapping ( "/list/accept/{id}" )
    public String acceptScholarship ( @PathVariable ( "id" ) Condition condition )
    {
        this.conditionService.acceptCondition ( condition );

        return "redirect:/conditions/list";
    }

    @Secured ( "ROLE_INSTRUCTOR" )
    @PostMapping ( "/list/reject" )
    public String rejectScholarship ( @Valid @ModelAttribute ( "condition" ) Condition condition, BindingResult bindingResult )
    {
        this.conditionService.rejectCondition ( condition );

        return "redirect:/conditions/list";
    }

    @Secured ( "ROLE_DEAN" )
    @GetMapping ( { "/open", "/close" } )
    public String openOrCloseScholarships ()
    {
        this.conditionService.openOrClose ();

        return "redirect:/conditions/list";
    }
}
