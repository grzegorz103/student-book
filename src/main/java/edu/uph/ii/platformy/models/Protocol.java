package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "protocols")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Protocol
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn (name = "subject_id")
        private Subject subject;

        @Enumerated (EnumType.STRING)
        @Column (name = "status")
        private Statuses status;

        @Column (name = "first_termin")
        private String firstTermin;

        @Column (name = "second_termin")
        private String secondTermin;
}
