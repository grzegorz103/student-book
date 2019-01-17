package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table ( name = "accounts" )
@Getter
@Setter
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "mail" )
    private String mail;

    @Column ( name = "password" )
    private String password;

    @OneToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "person" )
    private Person person;

    @ManyToMany ( fetch = FetchType.EAGER )
    @JoinTable ( name = "users_roles",
            joinColumns = @JoinColumn ( name = "user_id" ),
            inverseJoinColumns = @JoinColumn ( name = "role_id" ) )
    private Set< Role > roles;


    @Transient
    private String passwordConfirm;

    public User ( long id, String mail )
    {
        this.id = id;
        this.mail = mail;
    }
}
