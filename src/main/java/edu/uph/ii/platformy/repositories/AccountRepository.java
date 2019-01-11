package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Long>
{
        User findByMail ( String mail );
}
