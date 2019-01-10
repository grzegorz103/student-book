package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorListController
{
        @Autowired
        InstructorRepository instructorRepository;

        @RequestMapping(value = "/list")
        protected String handleRequest ( @ModelAttribute("vehicleSearch") VehicleFilter vehicleFilter,
                                         Model model,
                                         Pageable pageable )
        {
                Page p = vehicleFilter.isPhraseEmpty() && vehicleFilter.isPriceEmpty()
                        ? vehicleRepository.findAll( pageable )
                        //: vehicleRepository.findByModelContaining( vehicleFilter.getPhrase(), pageable );
                        //: vehicleRepository.findVehicles( vehicleFilter.getPhrase(), pageable );
                        : vehicleRepository.findAllQuery( vehicleFilter.getPhrase().toLowerCase()
                        , vehicleFilter.getPriceMin(), vehicleFilter.getPriceMax(), pageable );

                model.addAttribute( "list", p );

                return "vehicleList";
        }

        @GetMapping("/clear")
        public String clearFilter ( @ModelAttribute("vehicleSearch") VehicleFilter vehicleFilter )
        {
                vehicleFilter.setPhrase( "" );
                vehicleFilter.setPriceMin( null );
                vehicleFilter.setPriceMax( null );

                return "redirect:/vehicle/list";
        }


        @ModelAttribute("vehicleSearch")
        public VehicleFilter vehicleFilter ()
        {
                return new VehicleFilter();
        }


        @Secured("ROLE_ADMIN")
        @GetMapping("/delete/{id}")
        public String deleteVehicle (Model model, @PathVariable Long id )
        {
                vehicleRepository.deleteById( id );
                return "redirect:/vehicle/list";
        }

        @Secured({"ROLE_USER", "ROLE_ADMIN"})
        @GetMapping("/{id}")
        public String showDetails (Model model, @PathVariable Long id )
        {
                if (id == 0) model.addAttribute( "vehicle", new Vehicle() );
                else model.addAttribute( "vehicle", vehicleRepository.findById( id ).get() );
                return "vehicleDetails";
        }
}
