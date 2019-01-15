package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Scholarship;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AccountRepository;
import edu.uph.ii.platformy.repositories.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service ( "scholarshipService" )
public class ScholarshipServiceImpl implements ScholarshipService
{
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page< Scholarship > findScholarships ( Pageable pageable )
    {
        org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User ) SecurityContextHolder.getContext ()
                .getAuthentication ()
                .getPrincipal ();

        User myUser = accountRepository.findByMail ( user.getUsername () );

        for ( Role role : myUser.getRoles () )
        {
            if ( role.getUserType () == Role.UserTypes.ROLE_STUDENT )
            {
                return this.scholarshipRepository.findAllByStudent ( myUser.getPerson ()
                        .getId (), pageable );
            }

            if ( role.getUserType () == Role.UserTypes.ROLE_DEAN )
            {
                return this.scholarshipRepository.findAll ( pageable );
            }
        }
        return null;
    }
}
