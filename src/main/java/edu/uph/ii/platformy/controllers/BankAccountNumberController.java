package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@PreAuthorize ( "hasRole('ROLE_STUDENT')" )
@RequestMapping ( "/bankAccountNumber" )
public class BankAccountNumberController
{
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public BankAccountNumberController ( AccountRepository accountRepository, StudentRepository studentRepository )
    {
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
    }

    @RequestMapping ( "/show" )
    public String showBankAccountNumber ( Model model, @RequestParam ( name = "saved", required = false ) Boolean saved )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        model.addAttribute ( "student", myUser.getPerson () );
        model.addAttribute ( "saved", saved );


        return "bankAccountNumber";
    }

    @PostMapping ( "/save" )
    public String saveBankAccountNumber ( @Valid @ModelAttribute ( "student" ) Student student, BindingResult bindingResult )
    {
        if ( student != null )
        {
            String number = student.getBankAccountNumber ()
                    .trim ();

            if ( !number.isEmpty () )
            {
                student = this.studentRepository.findById ( student.getId () )
                        .orElse ( null );

                if ( student != null )
                {
                    student.setBankAccountNumber ( number );

                    this.studentRepository.save ( student );

                    return "redirect:/bankAccountNumber/show?saved=true";
                }
            }
        }

        return "redirect:/bankAccountNumber/show?save=false";
    }
}
