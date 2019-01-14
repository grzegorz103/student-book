package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table ( name = "conditions" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condition
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "condition_type" )
    @Enumerated ( EnumType.STRING )
    private ConditionTypes conditionTypes;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "subject_id" )
    private Subject subject;

    @Column ( name = "submitting_date" )
    @Temporal ( TemporalType.DATE )
    private Date submittingDate;

    @Column ( name = "status_change_date" )
    @Temporal ( TemporalType.DATE )
    private Date statusChangeDate;

    @Enumerated ( EnumType.STRING )
    private Statuses status;

    @Column ( name = "condition_justification", columnDefinition = "TEXT" )
    private String conditionJustification; //Uzasadnienie podania
}
