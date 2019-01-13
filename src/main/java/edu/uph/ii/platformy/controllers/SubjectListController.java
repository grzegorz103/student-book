package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectListController
{

        private final SubjectService subjectService;

        @Autowired
        public SubjectListController(SubjectService subjectService )
        {

                this.subjectService = subjectService;
        }

        @RequestMapping (value = "/list")
        protected String handleRequest ( Model model )
        {
                model.addAttribute( "list", subjectService.findAll() );

                return "subjectList";
        }

        @GetMapping (value = "/{id}/lessons/list")
        protected String lessonsList ( Model model, @PathVariable("id") Subject subject )
        {
                model.addAttribute( "list", subjectService.getLessons( subject ) );
                model.addAttribute( "subject", subject );

                return "lessonList";
        }

        @GetMapping (value = "/{id}/lessons/{lesson}")
        protected String attendancesList ( Model model, @PathVariable("lesson") Lesson lesson )
        {
                model.addAttribute( "list", subjectService.getAttendances( lesson ) );
                model.addAttribute( "lesson", lesson );

                return "attendanceList";
        }

        @GetMapping("/{sub}/lessons/{les}/attendances/{atd}/status/{sts}")
        public String changeAttendance (Model model,
                                    @PathVariable ("sub") Long sub,
                                    @PathVariable ("les") Long les,
                                    @PathVariable ("atd") Long atd,
                                    @PathVariable ("sts") Long sts)
        {
                subjectService.changeStudentAttendance(atd, sts);

                return "redirect:/subjects/"+sub+"/lessons/"+les;
        }

}
