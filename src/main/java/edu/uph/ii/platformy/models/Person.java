package edu.uph.ii.platformy.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person
{

        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @Column (name = "name")
        @NotBlank
        @NotEmpty
        @Size (min = 2, max = 25)
        private String name;

        @Column (name = "surname")
        @NotBlank
        @NotEmpty
        @Size (min = 2, max = 25)
        private String surname;

        @Column (name = "sex")
        private boolean sex;

}
