package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Protocol;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocolRepository extends JpaRepository<Protocol, Long>
{
        boolean existsBySubject ( Subject subject );

        Protocol findBySubject ( Subject subject );
}
