package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table (name = "Subject")
@Getter
@Setter
@AllArgsConstructor
public class Subject
{

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (name = "name")
        @NotBlank
        @NotEmpty
        @Size (min = 2, max = 25)
        private String name;

        @OneToMany
        @JoinColumn (name = "instructor_id")
        private Set<Instructor> instructor;

        @JoinColumn(name="course_of_study")
        @OneToMany
        private Set<Course_of_study> course_of_study;


}