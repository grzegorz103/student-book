package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table ( name = "deans" )
@PrimaryKeyJoinColumn ( name = "id" )
@Getter
@Setter
@AllArgsConstructor
public class Dean extends Person
{
    public Dean ( Long id, String name, String surname, boolean sex, Long pesel )
    {
        super ( id, name, surname, sex, pesel );
    }
}
