package dasturhub.uz.repository;

import dasturhub.uz.dtos.lesson.CreateAndEditLessonDto;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, String> {
    List<Lesson> section(Section section);

    @Query("SELECT s FROM Lesson s WHERE s.section.id = :sectionId ORDER BY s.order ASC")
    List<Lesson> findAllBySectionIdAndOrder(String sectionId);

}
