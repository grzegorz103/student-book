package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.Workshop;
import edu.uph.ii.platformy.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workshops")
public class WorkshopListController
{

        private final WorkshopService workshopService;

        @Autowired
        public WorkshopListController(WorkshopService workshopService )
        {
                this.workshopService = workshopService;
        }

        @RequestMapping (value = "/list")
        protected String handleRequest ( Model model )
        {
                model.addAttribute( "list", workshopService.findWorkshopList () );
                model.addAttribute( "student", workshopService.findActiveStudent () );
                return "workshopList";
        }

        @GetMapping("/{wks}/exit/{std}")
        public String deleteStdFromWks (Model model, @PathVariable ("wks") Workshop workshop,
                                             @PathVariable ("std") Student student)
        {
                workshopService.deleteStudentFromWorkshop(student,workshop);
                return "redirect:/workshops/list";
        }

        @GetMapping("/{wks}/join/{std}")
        public String addStdToWks (Model model, @PathVariable ("wks") Workshop workshop,
                                        @PathVariable ("std") Student student)
        {
                workshopService.addStudentToWorkshop(student,workshop);
                return "redirect:/workshops/list";
        }


}
