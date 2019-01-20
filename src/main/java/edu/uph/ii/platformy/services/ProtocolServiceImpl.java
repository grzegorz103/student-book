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
import java.util.Objects;
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
                        .filter( Objects::nonNull )
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


        @Override
        public List<Protocol> getAll ()
        {
                return protocolRepository.findAll()
                        .stream()
                        .sorted( ( o1, o2 ) -> ( int ) (o1.getId() - o2.getId()) )
                        .collect( Collectors.toList() );
        }


        @Override
        public List<Subject> getAwaitingSubjectsForProtocol ()
        {
                return subjectRepository.findAll()
                        .stream()
                        .filter( e -> !protocolRepository.existsBySubject( e ) )
                        .collect( Collectors.toList() );
        }

        @Override
        public void openAll ()
        {
                protocolRepository.findAll()
                        .stream()
                        .filter( e -> e.getStatus().equals( Statuses.PROTOCOLE_CLOSED ) )
                        .forEach( this::editState );

        }


        @Override
        public void editStateByProtocol ( Protocol protocol )
        {
                if ( protocol.getStatus().equals( Statuses.PROTOCOLE_CLOSED ) )
                        protocol.setStatus( Statuses.PROTOCOLE_OPEN );
                else if ( protocol.getStatus().equals( Statuses.PROTOCOLE_OPEN ) )
                        protocol.setStatus( Statuses.PROTOCOLE_ACCEPTED );
                else if ( protocol.getStatus().equals( Statuses.PROTOCOLE_ACCEPTED ) )
                        protocol.setStatus( Statuses.PROTOCOLE_CLOSED );
                protocolRepository.save( protocol );
        }

        @Override
        public List<Error> getAllErrors ()
        {
                return errorRepository.findAll();
        }


        private void editState ( Protocol protocol )
        {
                protocol.setStatus( Statuses.PROTOCOLE_OPEN );
                protocolRepository.save( protocol );
        }


        private Protocol mapSubjectToProtocol ( Subject subject )
        {
                Protocol protocol = new Protocol();
                protocol.setStatus( Statuses.PROTOCOLE_CLOSED );
                protocol.setSubject( subject );
                return protocol;
        }
}
