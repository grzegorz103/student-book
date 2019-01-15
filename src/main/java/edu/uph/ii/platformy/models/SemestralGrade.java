package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "semestral_grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemestralGrade
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "subject_id")
        private Subject subject;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "student_id")
        private Student student;

        @Column (name = "first_termin_grade")
        private Long firstTerminGrade;

        @Column (name = "second_termin_grade")
        private Long secondTerminGrade;

        @Column (name = "total_grade")
        private Long totalGrade;
}
