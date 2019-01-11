package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{
        List<Message> findAllByReceiver ( Person receiver );
}
