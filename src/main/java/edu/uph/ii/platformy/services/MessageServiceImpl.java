package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service ("messageService")
public class MessageServiceImpl implements MessageService
{
        private final AccountRepository accountRepository;

        private final MessageRepository messageRepository;


        @Autowired
        public MessageServiceImpl ( MessageRepository messageRepository, AccountRepository accountRepository )
        {
                this.messageRepository = messageRepository;
                this.accountRepository = accountRepository;
        }


        @Override
        public void send ( Message message )
        {
                if ( message.getReceiver().getId() == null )
                        message.setReceiver( accountRepository.findByMail( message.getReceiver().getMail() ) );
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                message.setDate( new Date() );
                message.setSender( accountRepository.findByMail( user.getUsername() ) );
                messageRepository.save( message );
        }


        @Override
        public List<Message> getMessages ()
        {
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User u = accountRepository.findByMail( user.getUsername() );
                return messageRepository.findAllByReceiver( u );
        }
}
