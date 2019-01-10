package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>
{
        Role findRoleByUserType(Role.UserTypes userType);
}
