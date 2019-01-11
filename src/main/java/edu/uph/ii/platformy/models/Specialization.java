package edu.uph.ii.platformy.models;

import javax.persistence.*;

@Entity
@Table (name = "specializations")
public class Specialization
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (name = "name")
        private String name;

        @Column (name = "limit")
        private Long limit;

}
