package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "roles")
@Data
@NoArgsConstructor
public class Role
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Integer id;

        @Enumerated (EnumType.STRING)
        private UserTypes userType;

        @ManyToMany (mappedBy = "roles")
        private Set<User> users;

        public Role ( UserTypes type )
        {
                this.userType = type;
        }

        public static enum UserTypes
        {
                ROLE_ADMIN,
                ROLE_USER,
                ROLE_INSTRUCTOR
        }
}
