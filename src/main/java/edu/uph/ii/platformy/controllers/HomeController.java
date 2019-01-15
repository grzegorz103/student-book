package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.services.declarations.InformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{
        private static final Logger logger = LogManager.getLogger( HomeController.class );

        private final InformationService informationService;

        @Autowired
        public HomeController ( InformationService informationService )
        {
                this.informationService = informationService;
        }

        @GetMapping (path = "/")
        public String home ( Model model, Pageable pageable )
        {
                model.addAttribute( "list", informationService.getAll( pageable ) );

                logger.info( "Wywolanie" );
                return "index";
        }

}
