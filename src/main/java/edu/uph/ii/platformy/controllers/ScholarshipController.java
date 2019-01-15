package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.services.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ( "/scholarships" )
public class ScholarshipController
{
    @Autowired
    private ScholarshipService scholarshipService;

    @RequestMapping ( "/list" )
    public String showScholarshipList ( Model model, Pageable pageable )
    {
        Page page = this.scholarshipService.findScholarships ( pageable );

        model.addAttribute ( "scholarshipList", page );

        return "scholarshipList";
    }
}
