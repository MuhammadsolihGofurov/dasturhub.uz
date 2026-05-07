package dasturhub.uz.repository;

import dasturhub.uz.entity.Course;
import dasturhub.uz.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, String> {
    List<Section> course(Course course);

    @Query("SELECT s FROM Section s WHERE s.course.id = :courseId ORDER BY s.order ASC")
    List<Section> findSectionsByCourse(@Param("courseId") String courseId);
}
