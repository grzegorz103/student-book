package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.SemestralGrade;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize ( "isAuthenticated()" )
@RequestMapping ( "/grades" )
public class GradesController
{
    private final SemestralGradeService semestralGradeService;

    @Autowired
    public GradesController ( SemestralGradeService semestralGradeService )
    {
        this.semestralGradeService = semestralGradeService;
    }

    @RequestMapping ( "/show" )
    public String showConditionList ( Model model )
    {
        List< SemestralGrade > semestralGrades = this.semestralGradeService.getSemestralGradesByStudent ();

        model.addAttribute ( "avg", this.semestralGradeService.getActualAvgGradesForStudent () );
        model.addAttribute ( "semestralGrades", semestralGrades );

        return "gradesList";
    }
}
