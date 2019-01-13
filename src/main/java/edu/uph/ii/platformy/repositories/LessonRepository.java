package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Lesson;
import edu.uph.ii.platformy.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long>
{

    List<Lesson> findAllBySubject(Subject subject);
}
