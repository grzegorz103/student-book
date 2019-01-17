package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                model.addAttribute( "list", subjectService.findSubjects() );

                return "subjectList";
        }

        @GetMapping (value = "/{id}/lessons/list")
        protected String lessonsList ( Model model, @PathVariable("id") Subject subject )
        {
                model.addAttribute( "list", subjectService.getLessons( subject ) );
                model.addAttribute( "subject", subject );

                return "lessonList";
        }

        @GetMapping (value = "/{id}/lessons/add")
        protected String addLessonForm ( Model model, @PathVariable("id") Subject subject )
        {
            model.addAttribute( "les", new Lesson() );
            model.addAttribute( "subject", subject );

            return "lessonForm";
        }

        @PostMapping("/{id}/lessons/add")
        public String addLessonFormProcess (@Valid @ModelAttribute("les") Lesson lesson,
                                   @ModelAttribute("subject") Subject subject,
                                   BindingResult result )
        {
            if ( result.hasErrors() ) return "lessonForm";

            subjectService.addLesson( lesson, subject );

            return "redirect:/subjects/"+subject.getId()+"/lessons/list";
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

        @GetMapping("/{sub}/lessons/{les}/attendances/add")
        public String addAttendanceList (Model model,
                                        @PathVariable ("sub") Subject sub,
                                        @PathVariable ("les") Lesson les)
        {
                subjectService.addAttendanceList(sub, les);

                return "redirect:/subjects/"+sub.getId()+"/lessons/"+les.getId();
        }

}
