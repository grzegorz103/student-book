package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table ( name = "Instructors" )
@PrimaryKeyJoinColumn ( name = "id" )
@Getter
@Setter
@AllArgsConstructor
public class Instructor extends Person
{
    public Instructor ( Long id, String name, String surname, boolean sex, Long pesel )
    {
        super ( id, name, surname, sex, pesel );
    }
}
