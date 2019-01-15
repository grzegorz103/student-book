package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.services.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/protocol")
public class ProtocolController
{
        private final ProtocolService protocolService;

        @Autowired
        public ProtocolController ( ProtocolService protocolService )
        {
                this.protocolService = protocolService;
        }

        @GetMapping ("/add")
        @Secured ("ROLE_DEAN")
        public String addProtocols ( Model model )
        {
                protocolService.addProtocols();
                return "redirect:/";
        }


        @GetMapping ("/list")
        @Secured ("ROLE_INSTRUCTOR")
        public String getProtocols ( Model model )
        {
                model.addAttribute( "list", protocolService.getProtocols() );
                return "protocolPage";
        }
}
