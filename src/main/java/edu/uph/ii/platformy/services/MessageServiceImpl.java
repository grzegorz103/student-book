package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.MessageRepository;
import edu.uph.ii.platformy.services.declarations.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        public boolean send ( Message message )
        {
                if ( message.getReceiver().getId() == null )
                        message.setReceiver( accountRepository.findByMail( message.getReceiver().getMail() ) );
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                message.setDate( new Date() );
                message.setDeleted( false );
                message.setSender( accountRepository.findByMail( user.getUsername() ) );
                message.setStatus( Statuses.MESSAGE_UNREAD );
                if ( message.getReceiver() != null )
                {
                        messageRepository.save( message );
                        return true;
                }
                return false;
        }


        @Override
        public List<Message> getMessages ()
        {
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User u = accountRepository.findByMail( user.getUsername() );
                return changeStatus( messageRepository.findAllByReceiver( u ) );
        }


        @Override
        public List<Message> getUserMessages ()
        {
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User u = accountRepository.findByMail( user.getUsername() );

                return messageRepository.findAllBySender( u );
        }

        @Override
        public void delete ( Message message )
        {
                message.setDeleted( true );
                messageRepository.save( message );
        }


        private List<Message> changeStatus ( List<Message> list )
        {
                list.forEach( e -> e.setStatus( Statuses.MESSAGE_READ ) );
                messageRepository.saveAll( list );
                return list.stream()
                        .filter( e -> !e.getDeleted() )
                        .collect( Collectors.toList() );
        }
}
