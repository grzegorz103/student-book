package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Protocol;
import edu.uph.ii.platformy.models.Statuses;
import edu.uph.ii.platformy.models.Subject;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.InstructorRepository;
import edu.uph.ii.platformy.repositories.ProtocolRepository;
import edu.uph.ii.platformy.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service ("protocolService")
public class ProtocolServiceImpl implements ProtocolService
{
        private final ProtocolRepository protocolRepository;

        private final SubjectRepository subjectRepository;

        private final AccountRepository accountRepository;

        private final InstructorRepository instructorRepository;

        @Autowired
        public ProtocolServiceImpl ( ProtocolRepository protocolRepository,
                                     SubjectRepository subjectRepository,
                                     AccountRepository accountRepository,
                                     InstructorRepository instructorRepository )
        {
                this.protocolRepository = protocolRepository;
                this.subjectRepository = subjectRepository;
                this.accountRepository = accountRepository;
                this.instructorRepository = instructorRepository;
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

        private Protocol mapSubjectToProtocol ( Subject subject )
        {
                Protocol protocol = new Protocol();
                protocol.setStatus( Statuses.PROTOCOLE_CLOSED );
                protocol.setSubject( subject );
                return protocol;
        }
}
