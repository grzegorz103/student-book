package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
        void save ( Person user );

        boolean isLoginAvailable ( String value );
}
