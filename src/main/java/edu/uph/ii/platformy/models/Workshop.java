package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Workshops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workshop
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column (name = "limitPlaces")
    private Long limit;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany ( fetch = FetchType.EAGER )
    @JoinTable ( name = "workshops_students",
            joinColumns = @JoinColumn ( name = "workshop_id" ),
            inverseJoinColumns = @JoinColumn ( name = "student_id" ) )
    private Set< Student > students;

}
