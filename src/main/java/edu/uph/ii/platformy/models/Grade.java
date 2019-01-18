package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Grade
{
        private Long id;
        private Long firstGrade;
        private Long secondGrade;
        private Long totalGrade;
}
