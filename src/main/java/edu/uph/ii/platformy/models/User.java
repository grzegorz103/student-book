package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "accounts")
@Data
@NoArgsConstructor
public class User
{
        @Id
        private Long id;

        @Column (name = "mail")
        private String mail;

        @Column (name = "password")
        private String password;

        @OneToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "person")
        private Person person;

        @ManyToMany (fetch = FetchType.EAGER)
        @JoinTable (name = "users_roles",
                joinColumns = @JoinColumn (name = "user_id"),
                inverseJoinColumns = @JoinColumn (name = "role_id"))
        private Set<Role> roles;


        @Transient
        private String passwordConfirm;

}
