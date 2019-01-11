package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "specializations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialization
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (name = "name")
        private String name;

        @Column (name = "limitPlaces")
        private Long limit;

}
