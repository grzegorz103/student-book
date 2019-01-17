package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table (name = "Subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

        @Column(name = "semester")
        private Long semester;

        @ManyToOne
        @JoinColumn(name="course_id")
        private Course course;

        @ManyToOne
        @JoinColumn(name="instructor_id")
        private Instructor instructor;


}