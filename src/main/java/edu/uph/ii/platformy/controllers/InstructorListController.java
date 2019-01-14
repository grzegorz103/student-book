package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorListController
{

        private final InstructorService instructorService;

        @Autowired
        public InstructorListController ( InstructorService instructorService )
        {
                this.instructorService = instructorService;
        }

        @RequestMapping (value = "/list")
        protected String handleRequest ( Model model )
        {
                model.addAttribute( "list", instructorService.findInstructors() );

                return "instructorList";
        }

}
