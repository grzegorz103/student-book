package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "date")
    private Date lessonDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
