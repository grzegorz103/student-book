package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class Message
{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @OneToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "sender")
        private User sender;

        @OneToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "receiver")
        private User receiver;

        @Temporal (TemporalType.DATE)
        @Column (name = "date")
        private Date date;

        @Column (name = "text")
        private String text;
}
