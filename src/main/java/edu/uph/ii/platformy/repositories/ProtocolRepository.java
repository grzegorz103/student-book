package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Protocol;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProtocolRepository extends JpaRepository<Protocol, Long>
{
        boolean existsBySubject ( Subject subject );

        Protocol findBySubject ( Subject subject );
}
