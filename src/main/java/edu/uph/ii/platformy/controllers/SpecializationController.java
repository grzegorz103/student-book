package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.services.declarations.InformationService;
import edu.uph.ii.platformy.services.declarations.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
        private final InformationService informationService;

        @Autowired
        public SpecializationController ( SpecializationService specializationService, AccountRepository accountRepository, InformationService informationService )
        {
                this.specializationService = specializationService;
                this.accountRepository = accountRepository;
                this.informationService = informationService;
        }


        @GetMapping ("/choose")
        @Secured ("ROLE_STUDENT")
        public String specializationChoose ( Model model )
        {
                if ( !specializationService.isEnabled() || specializationService.isUserSpecialization() )
                        return "redirect:/";
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Student currentStudent = ( Student ) u.getPerson();

                model.addAttribute( "list", specializationService.getSpecializationById( currentStudent ) );
                model.addAttribute( "student", currentStudent );
                return "specializationPage";
        }

        @PostMapping ("/choose")
        @Secured ("ROLE_STUDENT")
        public String saveSpecializationChoice ( @ModelAttribute ("student") Student specialization,
                                                 @RequestParam ("id") Student s )
        {
                s.setSpecialization( specialization.getSpecialization() );
                specializationService.addStudentSpecializaion( s );
                return "redirect:/";
        }


        @GetMapping ("/edit")
        @Secured ("ROLE_DEAN")
        public String getState ( Model model )
        {
                model.addAttribute( "state", specializationService.isEnabled() );
                model.addAttribute( "list", specializationService.getStudentsWithoutSpecialization() );
                return "editSpecialization";
        }

        @GetMapping ("/edit/state")
        @Secured ("ROLE_DEAN")
        public String editState ( Model model )
        {
                specializationService.editStatus();
                return "redirect:/sp/edit";
        }


        @GetMapping ("/{id}")
        @PreAuthorize ("isAuthenticated()")
        public String infoDetails ( Model model,
                                    @PathVariable ("id") Long id )
        {
                model.addAttribute( "sp", informationService.getById( id ) );
                return "details";
        }
}
