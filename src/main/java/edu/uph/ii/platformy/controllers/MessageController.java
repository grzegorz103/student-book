package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/msg")
public class MessageController
{
        private final MessageService messageService;


        @Autowired
        public MessageController ( MessageService messageService )
        {
                this.messageService = messageService;
        }


        @GetMapping ("/list")
        public String getMessages ( Model model )
        {
                messageService.send( new Message() );
                model.addAttribute( "list", messageService.getMessages() );
                return "messageList";
        }
}
