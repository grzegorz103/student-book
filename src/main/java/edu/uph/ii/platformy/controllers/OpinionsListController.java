package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class OpinionsListController
{
    @Autowired
    OpinionRepository opinionRepository;
    InstructorRepository instructorRepository;

    @GetMapping("/opinions/{id}")
    public String handleRequest (Model model, @PathVariable Long id )
    {
        Instructor instructor = instructorRepository.findById(id);
        model.addAttribute( "list", opinionRepository.findAllByInstructor( instructor ) );
        return "opinionList";
    }

}
