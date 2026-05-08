package dasturhub.uz.services.lesson;

import dasturhub.uz.dtos.lesson.CreateAndEditLessonDto;
import dasturhub.uz.entity.Lesson;

import java.util.List;

public interface ILessonService {

    List<Lesson> findAllBySectionId(String sectionId);

    void saveLessson(String sectionId, CreateAndEditLessonDto lessonDto);

    Lesson getLessonById(String lessonId);

    void updateLesson(String lessonId, CreateAndEditLessonDto lessonDto);

    void deleteLesson(String lessonId);
}
