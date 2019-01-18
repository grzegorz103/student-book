package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table ( name = "scholarships" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scholarship
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "scholarship_type" )
    @Enumerated ( EnumType.STRING )
    private ScholarshipTypes scholarshipType;

    @Column ( name = "people_number" )
    private Integer peopleNumber = 0; //Liczba osób w rodzinie studenta (razem ze studentem) - 0 jeśli stypendium naukowe

    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal allMembersIncome; //wspólny dochód wszystkich członków rodziny

    @NumberFormat ( style = NumberFormat.Style.CURRENCY )
    private BigDecimal amount = null; //przyznana kwota stypendium

    @Column ( name = "average_grade" )
    private double averageGrade = 0.0d; //średnia ocen z poprzedniego roku (tylko stypendium naukowe)

    private Long semester;

    @Column ( name = "submitting_date" )
    @Temporal ( TemporalType.TIMESTAMP )
    private Date submittingDate;

    @Column ( name = "status_change_date" )
    @Temporal ( TemporalType.TIMESTAMP )
    private Date statusChangeDate;

    @Enumerated ( EnumType.STRING )
    private Statuses status;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "student_id" )
    private Student student;
}
