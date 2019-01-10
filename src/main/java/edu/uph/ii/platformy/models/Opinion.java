package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "Opinions")
@Getter
@Setter
@AllArgsConstructor
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "status")
    private Statuses status;

    @Column(name = "opinion")
    private String opinion;





}