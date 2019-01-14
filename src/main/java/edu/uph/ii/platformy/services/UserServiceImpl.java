package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.config.ProfileNames;
import edu.uph.ii.platformy.models.Instructor;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Student;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.RoleRepository;
import edu.uph.ii.platformy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.uph.ii.platformy.repositories.InstructorRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile ( ProfileNames.DATABASE )
public class UserServiceImpl implements UserService
{

    private final PasswordEncoder passwordEncoder;

    private final InstructorRepository instructorRepository;

    private final RoleRepository roleRepository;

    private final AccountRepository accountRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public UserServiceImpl ( PasswordEncoder passwordEncoder, InstructorRepository instructorRepository, RoleRepository roleRepository, AccountRepository accountRepository, StudentRepository studentRepository )
    {
        this.passwordEncoder = passwordEncoder;
        this.instructorRepository = instructorRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional ( readOnly = true )
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException
    {

        edu.uph.ii.platformy.models.User user = accountRepository.findByMail ( username );
        if ( user == null )
            throw new UsernameNotFoundException ( username );

        return convertToUserDetails ( user );
    }

    private UserDetails convertToUserDetails ( edu.uph.ii.platformy.models.User user )
    {
        //   user.setEnabled( false );
        Set< GrantedAuthority > grantedAuthorities = user.getRoles ()
                .stream ()
                .map ( role -> new SimpleGrantedAuthority ( role.getUserType ()
                        .toString () ) )
                .collect ( Collectors.toSet () );

        return new User ( user.getMail (), user.getPassword (), grantedAuthorities );
    }

    @Override
    @Transactional
    public void save ( edu.uph.ii.platformy.models.User user )
    {
        user.setPassword ( this.passwordEncoder.encode ( user.getPassword () ) );
        user.setRoles ( new HashSet<> ( Arrays.asList ( roleRepository.findRoleByUserType ( Role.UserTypes.ROLE_STUDENT ) ) ) );
        user.setPasswordConfirm ( null );
        Student s = ( Student ) user.getPerson ();
        studentRepository.save ( s );
        accountRepository.save ( user );
    }

    @Override
    public boolean isLoginAvailable ( String value )
    {
        return this.instructorRepository.findByName ( value ) == null;
    }

}