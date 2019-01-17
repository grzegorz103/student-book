package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
        void save ( User user );

        boolean isLoginAvailable ( String value );
}
