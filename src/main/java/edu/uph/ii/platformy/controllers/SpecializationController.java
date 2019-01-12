package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/sp")
public class SpecializationController
{
        private final SpecializationService specializationService;

        @Autowired
        public SpecializationController ( SpecializationService specializationService )
        {
                this.specializationService = specializationService;
        }


        @GetMapping ("/choose")
        public String specializationChoose ( Model model )
        {
                model.addAttribute( "list", specializationService.getAllSpecializations() );
                return "specializationPage";
        }

        @PostMapping ("/choose")
        public String saveSpecializationChoice ( @ModelAttribute ("form") Specialization specialization )
        {
                specializationService.addStudentSpecializaion( specialization );
                return "redirect:/";
        }

}
