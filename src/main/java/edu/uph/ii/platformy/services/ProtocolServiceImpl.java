package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.models.Error;
import edu.uph.ii.platformy.repositories.*;
import edu.uph.ii.platformy.services.declarations.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service ("protocolService")
public class ProtocolServiceImpl implements ProtocolService
{
        private final ProtocolRepository protocolRepository;

        private final SubjectRepository subjectRepository;

        private final AccountRepository accountRepository;

        private final InstructorRepository instructorRepository;

        private final ErrorRepository errorRepository;

        @Autowired
        public ProtocolServiceImpl ( ProtocolRepository protocolRepository,
                                     SubjectRepository subjectRepository,
                                     AccountRepository accountRepository,
                                     InstructorRepository instructorRepository, ErrorRepository errorRepository )
        {
                this.protocolRepository = protocolRepository;
                this.subjectRepository = subjectRepository;
                this.accountRepository = accountRepository;
                this.instructorRepository = instructorRepository;
                this.errorRepository = errorRepository;
        }

        @Override
        public void addProtocols ()
        {
                subjectRepository.findAll()
                        .stream()
                        .filter( e -> !protocolRepository.existsBySubject( e ) )
                        .map( this::mapSubjectToProtocol )
                        .forEach( protocolRepository::save );
        }

        @Override
        public List<Protocol> getProtocols ()
        {
                User user = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                edu.uph.ii.platformy.models.User u = accountRepository.findByMail( user.getUsername() );
                Instructor instructor = ( Instructor ) u.getPerson();

                return subjectRepository.findAllByInstructor( instructor )
                        .stream()
                        .map( protocolRepository::findBySubject )
                        .collect( Collectors.toList() );
        }


        @Override
        public void update ( ProtocolItem item )
        {
                Protocol p = protocolRepository.findById( item.getProtocolId() ).get();
                p.setFirstTermin( item.getFirstTermin() );
                p.setSecondTermin( item.getSecondTermin() );
                protocolRepository.save( p );
        }


        @Override
        public void addError ( Error error )
        {
                if ( error != null )
                {
                        error.setDate( new Date() );
                        errorRepository.save( error );
                }
        }

        private Protocol mapSubjectToProtocol ( Subject subject )
        {
                Protocol protocol = new Protocol();
                protocol.setStatus( Statuses.PROTOCOLE_CLOSED );
                protocol.setSubject( subject );
                return protocol;
        }
}
