package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "errors")
@Getter
@Setter
@NoArgsConstructor
public class Error
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "protocol_id")
        private Protocol protocol;

        @Temporal (TemporalType.DATE)
        @Column (name = "error_date")
        private Date date;

        @Column (name = "text")
        private String text;
}
