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

        @Bean
        InitializingBean init ()
        {
                return () -> {
                        if ( roleRepository.findAll().isEmpty() )
                        {
                                try
                                {
                                        Role roleUser = roleRepository.save( new Role( Role.UserTypes.ROLE_USER ) );
                                        Role roleAdmin = roleRepository.save( new Role( Role.UserTypes.ROLE_ADMIN ) );
                                          /*
                                        User user = new User("user", true);
                                        user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                                        user.setPassword(passwordEncoder.encode("user"));

                                        User admin = new User("admin", true);
                                        admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                                        admin.setPassword(passwordEncoder.encode("admin"));

                                        User test = new User("test", true);
                                        test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                                        test.setPassword(passwordEncoder.encode("test"));

                                        instructorRepository.save(user);
                                        instructorRepository.save(admin);
                                        instructorRepository.save(test);
                                        */
                                } catch ( Exception e )
                                {
                                        e.printStackTrace();
                                }
                        }

                };

        }
}