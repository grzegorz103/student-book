package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Error;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.services.declarations.ProtocolService;
import edu.uph.ii.platformy.services.declarations.SemestralGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/protocol")
public class ProtocolController
{
        private final ProtocolService protocolService;

        private final SemestralGradeService semestralGradeService;

        @Autowired
        public ProtocolController ( ProtocolService protocolService,
                                    SemestralGradeService semestralGradeService )
        {
                this.protocolService = protocolService;
                this.semestralGradeService = semestralGradeService;
        }

        @GetMapping ("/add")
        @Secured ("ROLE_DEAN")
        public String addProtocols ( Model model )
        {
                protocolService.addProtocols();
                return "redirect:/protocol/all";
        }


        @GetMapping ("/list")
        @Secured ("ROLE_INSTRUCTOR")
        public String getProtocols ( Model model )
        {
                model.addAttribute( "error", new Error() );
                model.addAttribute( "list", protocolService.getProtocols() );
                model.addAttribute( "protForm", new ProtocolItem() );
                return "protocolPage";
        }


        @PostMapping ("/edit")
        @Secured ("ROLE_INSTRUCTOR")
        public String editProtocol ( @ModelAttribute ("protForm") ProtocolItem item,
                                     @RequestParam ("id") Long id )
        {
                item.setProtocolId( id );
                protocolService.update( item );
                return "redirect:/protocol/list";
        }


        @Secured ("ROLE_INSTRUCTOR")
        @RequestMapping ("/gradeslist")
        public String showGrades ( Model model,
                                   @RequestParam ("id") Instructor id,
                                   @RequestParam ("subj") Subject subject)
        {
                model.addAttribute( "grade", new Grade() );
                model.addAttribute( "list", semestralGradeService.getSemestralGradesBySubject( subject ) );
                return "semestralGradesPage";
        }


        @Secured ("ROLE_INSTRUCTOR")
        @PostMapping ("/grades")
        public String changeGrades ( @ModelAttribute ("grade") Grade grade,
                                     @RequestParam ("grade") SemestralGrade semestralGrade,
                                     @RequestParam("id") int id,
                                     @RequestParam("subj") int subject)
        {
                semestralGrade.setFirstTerminGrade( grade.getFirstGrade() );
                semestralGrade.setSecondTerminGrade( grade.getSecondGrade() );
                semestralGrade.setTotalGrade( grade.getTotalGrade() );
                semestralGradeService.changeGrades( semestralGrade );
                return "redirect:/protocol/gradeslist?id=" + id +"&subj=" + subject;
        }


        @PostMapping ("/error")
        @Secured ("ROLE_INSTRUCTOR")
        public String addError ( @ModelAttribute ("error") Error error )
        {
                protocolService.addError( error );
                return "redirect:/protocol/list";
        }


        @GetMapping ("/all")
        @Secured ("ROLE_DEAN")
        public String getAllProtocols ( Model model )
        {
                model.addAttribute( "list", protocolService.getAll() );
                model.addAttribute( "awaiting", protocolService.getAwaitingSubjectsForProtocol() );
                model.addAttribute( "errors", protocolService.getAllErrors() );
                return "protocolDean";
        }


        @Secured ("ROLE_DEAN")
        @GetMapping ("/open")
        public String openProtocols ( Model model )
        {
                protocolService.openAll();
                return "redirect:/protocol/all";
        }


        @GetMapping ("/editState/{id}")
        @Secured ("ROLE_DEAN")
        public String editProtocolState ( @PathVariable ("id") Protocol protocol )
        {
                protocolService.editStateByProtocol( protocol );
                return "redirect:/protocol/all";
        }
}
