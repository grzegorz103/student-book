package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "news")
@Data
@NoArgsConstructor
public class Information
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (name = "title")
        private String title;

        @Column (name = "text")
        private String text;

        @Column (name = "add_date")
        @Temporal (TemporalType.DATE)
        private Date date;

        @Column (name = "author")
        private String author;
}
