package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.ProtocolItem;
import edu.uph.ii.platformy.models.Subject;
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
                return "redirect:/";
        }


        @GetMapping ("/list")
        @Secured ("ROLE_INSTRUCTOR")
        public String getProtocols ( Model model )
        {
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
        @PostMapping ("/grades")
        public String showGrades ( Model model,
                                   @RequestParam ("id") Instructor id,
                                   @RequestParam ("subj") Subject subject )
        {
                model.addAttribute( "list", semestralGradeService.getSemestralGradesBySubject( subject ) );
                return "semestralGradesPage";
        }
}
