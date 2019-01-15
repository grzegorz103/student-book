package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.declarations.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/msg")
@PreAuthorize ( "isAuthenticated()" )
public class MessageController
{
        private final MessageService messageService;


        @Autowired
        public MessageController ( MessageService messageService )
        {
                this.messageService = messageService;
        }


        @GetMapping ()
        public String messagePage ()
        {
                return "messagePage";
        }


        @GetMapping ("/list")
        public String getMessages ( Model model )
        {
                model.addAttribute( "list", messageService.getMessages() );
                return "messageList";
        }


        @GetMapping ("/send")
        public String sendForm ( Model model )
        {
                model.addAttribute( "message", new Message() );
                return "sendMsgPage";
        }


        @PostMapping ("/send")
        public String sendMessage ( @ModelAttribute ("message") Message message,
                                    Model model )
        {
                messageService.send( message );
                return "redirect:/";
        }


        @GetMapping ("/reply/{id}")
        public String reply ( Model model,
                              @PathVariable ("id") User user )
        {
                Message message = new Message();
                message.setReceiver( user );
                model.addAttribute( "message", message );
                return "sendMsgPage";
        }


        @GetMapping ("/sent")
        public String showSentMessages ( Model model )
        {
                model.addAttribute( "list", messageService.getUserMessages() );
                return "sentMessagesPage";
        }
}
