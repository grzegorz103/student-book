package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.uph.ii.platformy.repositories.*;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer
{

        @Autowired
        private InstructorRepository instructorRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

}