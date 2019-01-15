package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table ( name = "Students" )
@PrimaryKeyJoinColumn ( name = "id" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person
{

    @Column ( name = "age" )
    private Integer age;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "specialization_id" )
    private Specialization specialization;

    @Column ( name = "spec_choosen" )
    private Boolean specChosen;

    @Column ( name = "semester" )
    private Long semester;

    @ManyToOne
    @JoinColumn ( name = "course_id" )
    private Course course;

    private String bankAccountNumber; //numer konta bankowego (w rzeczywistości 26 cyfr)

    @ManyToMany ( mappedBy = "students" )
    private Set< Workshop > workshops;

    public Student ( Long id, String name, String surname, boolean sex, Long pesel )
    {
        super ( id, name, surname, sex, pesel );
    }
}
