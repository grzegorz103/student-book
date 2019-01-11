package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorListController
{
        @Autowired
        InstructorRepository instructorRepository;

        @RequestMapping (value = "/list")
        protected String handleRequest ( Model model )
        {
                model.addAttribute( "list", instructorRepository.findAll() );

                return "instructorList";
        }

}
