package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table (name = "Students")
@PrimaryKeyJoinColumn (name = "id")
@Getter
@Setter
@AllArgsConstructor
public class Student extends Person
{

        @Column (name = "age")
        private Integer age;
}
