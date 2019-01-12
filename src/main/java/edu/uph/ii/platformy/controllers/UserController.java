package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.SpecializationService;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController
{
        private final UserService userService;
private SpecializationService specializationService;
        @Autowired
        public UserController ( UserService userService, SpecializationService specializationService )
        {
                this.userService = userService;
                this.specializationService = specializationService;
        }


        @GetMapping ("/register")
        public String registerForm ( Model model )
        {

                return "/registerForm";
        }


        @PostMapping ("/register")
        public String registerUser ( @ModelAttribute ("form") User user,
                                     Model model )
        {
                userService.save( user );
                return "redirect:/";
        }
}
