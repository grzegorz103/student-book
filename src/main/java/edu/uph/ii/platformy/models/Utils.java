package edu.uph.ii.platformy.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;

@Entity
@Table (name = "utils")
@Data
@NoArgsConstructor
public class Utils
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (name = "specialization_enabled")
        private Boolean specialization_enabled;

}
