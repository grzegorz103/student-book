package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
        private static final Logger logger = LogManager.getLogger( HomeController.class );

        @GetMapping (path = "/")
        public String home ( Model model )
        {
                model.addAttribute( "hello_world", "Hello world!" );

                logger.info( "Wywolanie" );
                return "index";
        }

}
