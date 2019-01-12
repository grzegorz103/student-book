package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Specialization;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Student currentStudent = ( Student ) u.getPerson();

                model.addAttribute( "list", specializationService.getAllSpecializations() );
                model.addAttribute( "student", currentStudent );
                return "specializationPage";
        }

        @PostMapping ("/choose")
        public String saveSpecializationChoice ( @ModelAttribute ("form") Specialization specialization )
        {
                specializationService.addStudentSpecializaion( specialization );
                return "redirect:/";
        }

}
