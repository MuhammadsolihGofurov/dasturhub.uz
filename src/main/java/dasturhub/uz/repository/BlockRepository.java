package dasturhub.uz.repository;

import dasturhub.uz.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, String> {


    @Query("SELECT s FROM Block s WHERE s.lesson.id = :lessonId ORDER BY s.order ASC")
    List<Block> getAllByLessonIdAndOrder(String lessonId);
}
