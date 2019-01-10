package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.config.ProfileNames;
import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.uph.ii.platformy.repositories.InstructorRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile (ProfileNames.DATABASE)
public class UserServiceImpl implements UserService
{

        private final PasswordEncoder passwordEncoder;

        private final InstructorRepository instructorRepository;

        private final RoleRepository roleRepository;

        @Autowired
        public UserServiceImpl ( PasswordEncoder passwordEncoder, InstructorRepository instructorRepository, RoleRepository roleRepository )
        {
                this.passwordEncoder = passwordEncoder;
                this.instructorRepository = instructorRepository;
                this.roleRepository = roleRepository;
        }

        @Transactional (readOnly = true)
        @Override
        public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException
        {

                edu.uph.ii.platformy.models.Person user = instructorRepository.findByName( username );

                if ( user == null )
                        throw new UsernameNotFoundException( username );

                return convertToUserDetails( user );
        }

        private UserDetails convertToUserDetails ( edu.uph.ii.platformy.models.Person user )
        {

                //   user.setEnabled( false );
                Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                        .stream()
                        .map( role -> new SimpleGrantedAuthority( role.getUserType().toString() ) )
                        .collect( Collectors.toSet() );

                return new User( user.getName(), user.getPassword(), grantedAuthorities );
        }

        @Override
        public void save ( edu.uph.ii.platformy.models.Person user ) // person, bo bedziemy zapisywac rozne typy potomnych uzytkownikow
        {
                user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
                user.setRoles( new HashSet<>( Arrays.asList( roleRepository.findRoleByUserType( Role.UserTypes.ROLE_USER ) ) ) );
                user.setPasswordConfirm( null );
                instructorRepository.save( ( Instructor ) user );       // poki co instructor
        }

        @Override
        public boolean isLoginAvailable ( String value )
        {
                return this.instructorRepository.findByName( value ) == null;
        }

}