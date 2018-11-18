package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{
        List<Message> findAllByReceiver ( User receiver );

        List<Message> findAllBySender ( User sender );

        boolean existsByReceiver ( User receiver );
}
