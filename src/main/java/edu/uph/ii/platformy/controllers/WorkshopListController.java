package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        @GetMapping("/add")
        public String addWorkshop (Model model)
        {
            model.addAttribute ( "workshop", new Workshop() );
            return "workshopForm";
        }

        @PostMapping("/add")
        public String workshopAddForm (@Valid @ModelAttribute("workshop") Workshop workshop,
                                   BindingResult result )
        {
            if ( result.hasErrors() ) return "workshopForm";


            workshopService.addWorkshop( workshop, workshopService.findActiveInstructor() );

            return "redirect:/workshops/list";
        }

        @GetMapping("/{wks}/delete")
        public String deleteWorkshop (Model model, @PathVariable ("wks") Workshop workshop)
        {
                workshopService.deleteWorkshop(workshop);
                return "redirect:/workshops/list";
        }

        @GetMapping("/{wks}/units")
        public String unitList (Model model, @PathVariable ("wks") Workshop workshop)
        {
                model.addAttribute ( "workshop", workshop );

                return "workshopUnitList";
        }

        @GetMapping("/{wks}/units/add")
        public String addUnit (Model model, @PathVariable ("wks") Workshop workshop)
        {
                model.addAttribute ( "workshop", workshop );
                model.addAttribute ( "unit", new Unit() );
                return "unitForm";
        }

        @PostMapping("/{wks}/units/add")
        public String unitAddForm (@Valid @ModelAttribute("unit") Unit unit, @PathVariable ("wks") Workshop workshop,
                                       BindingResult result )
        {
                if ( result.hasErrors() ) return "workshopForm";


                workshopService.addUnit( unit, workshop );

                return "redirect:/workshops/list";
        }


}
