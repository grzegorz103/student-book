package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping ("/instructors")
public class OpinionsListController
{

        private final InstructorService instructorService;

        @Autowired
        public OpinionsListController ( InstructorService instructorService )
        {
                this.instructorService = instructorService;
        }

        @GetMapping ("/{id}/opinions")
        public String handleRequest ( Model model, @PathVariable ("id") Instructor instructor )
        {

                model.addAttribute( "list", instructorService.getOpinions( instructor ) );
                model.addAttribute( "id", instructor.getId() );
                return "opinionList";
        }

        @GetMapping("/{id}/opinions/add")
        public String showForm (Model model, @PathVariable ("id") Instructor instructor )
        {
                model.addAttribute ( "opn", new Opinion() );
                model.addAttribute( "instructor", instructor );

                return "opinionForm";
        }

        @PostMapping("/{id}/opinions/add")
        public String processForm (@Valid @ModelAttribute("opn") Opinion opinion,
                                   @ModelAttribute("instructor") Instructor instructor,
                                   BindingResult result )
        {
                if ( result.hasErrors() ) return "opinionForm";

                instructorService.addOpinion( opinion, instructor );

                return "redirect:/instructors/list";
        }

        @GetMapping("/{ins}/opinions/{opn}/status/{sta}")
        public String changeStatus (Model model,
                                    @PathVariable ("ins") Long ins,
                                    @PathVariable ("opn") Long opn,
                                    @PathVariable ("sta") Long sta)
        {
            instructorService.changeOpinionStatus(opn, sta);

            return "redirect:/instructors/"+ins+"/opinions";
        }

}
