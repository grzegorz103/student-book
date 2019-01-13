package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/sp")
public class SpecializationController
{
        private final SpecializationService specializationService;
        private final AccountRepository accountRepository;

        @Autowired
        public SpecializationController ( SpecializationService specializationService, AccountRepository accountRepository )
        {
                this.specializationService = specializationService;
                this.accountRepository = accountRepository;
        }


        @GetMapping ("/choose")
        public String specializationChoose ( Model model )
        {
                if ( !specializationService.isEnabled() || specializationService.isUserSpecialization() )
                        return "redirect:/";
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Student currentStudent = ( Student ) u.getPerson();

                model.addAttribute( "list", specializationService.getAllSpecializations() );
                model.addAttribute( "student", currentStudent );
                return "specializationPage";
        }

        @PostMapping ("/choose")
        public String saveSpecializationChoice ( @ModelAttribute ("student") Student specialization,
                                                 @RequestParam ("id") Student s )
        {
                s.setSpecialization( specialization.getSpecialization() );
                specializationService.addStudentSpecializaion( s );
                return "redirect:/";
        }


        @GetMapping ("/edit")
        public String getState ( Model model )
        {
                model.addAttribute( "state", specializationService.isEnabled() );
                return "editSpecialization";
        }

        @GetMapping ("/edit/state")
        public String editState ( Model model )
        {
                specializationService.editStatus();
                return "redirect:/sp/edit";
        }
}
