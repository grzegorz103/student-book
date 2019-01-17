package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "Units")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    @Column (name = "description")
    private String description;

    @Column (name = "file_link")
    private String file;
}
