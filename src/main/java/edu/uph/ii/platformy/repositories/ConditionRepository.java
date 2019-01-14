package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository< Condition, Long >
{
}
