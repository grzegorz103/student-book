package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import edu.uph.ii.platformy.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/instructors")
public class OpinionsListController
{
        //   @Autowired
        // OpinionRepository opinionRepository;
        // @Autowired
        //   InstructorRepository instructorRepository;

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
                //   Instructor instructor = instructorRepository.findById( id ).get();
                // model.addAttribute( "list", opinionRepository.findAllByInstructor( instructor ) );
                model.addAttribute( "list", instructorService.getOpinions( instructor ) );
                return "opinionList";
        }

}
