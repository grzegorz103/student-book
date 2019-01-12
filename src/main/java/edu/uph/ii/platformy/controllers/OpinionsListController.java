package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping ("/opinions/{id}")
        public String handleRequest ( Model model,
                                      @PathVariable ("id") Instructor instructor )
        {

                model.addAttribute( "list", instructorService.getOpinions( instructor ) );
                return "opinionList";
        }

}
