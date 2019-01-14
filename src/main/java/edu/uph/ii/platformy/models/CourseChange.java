package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table ( name = "course_changes" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseChange
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "new_course_id" )
    private Course newCourse;

    @Column ( name = "course_change_justification", columnDefinition = "TEXT" )
    private String courseChangeJustification; //Uzasadnienie podania

    @Column ( name = "submitting_date" )
    @Temporal ( TemporalType.DATE )
    private Date submittingDate;

    @Column ( name = "status_change_date" )
    @Temporal ( TemporalType.DATE )
    private Date statusChangeDate;

    @Enumerated ( EnumType.STRING )
    private Statuses status;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "student_id" )
    private Student student;
}
