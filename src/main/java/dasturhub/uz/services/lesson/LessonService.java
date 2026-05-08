package dasturhub.uz.services.lesson;

import dasturhub.uz.dtos.lesson.CreateAndEditLessonDto;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.entity.Section;
import dasturhub.uz.repository.LessonRepository;
import dasturhub.uz.services.section.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ISectionService sectionService;


    @Override
    public List<Lesson> findAllBySectionId(String sectionId) {

        return lessonRepository.findAllBySectionIdAndOrder(sectionId);
    }

    @Override
    public void saveLessson(String sectionId, CreateAndEditLessonDto lessonDto) {
        Section section = sectionService.getSectionById(sectionId);
        Lesson newLesson = new Lesson();

        newLesson.setSection(section);
        newLesson.setOrder(lessonDto.getOrder());
        newLesson.setTitle(lessonDto.getTitle());
        newLesson.setDescription(lessonDto.getDescription());

        lessonRepository.save(newLesson);
    }

    @Override
    public Lesson getLessonById(String lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow( ()-> new RuntimeException("section not found"));
    }

    @Override
    public void updateLesson(String lessonId, CreateAndEditLessonDto lessonDto) {
        Lesson lesson =  getLessonById(lessonId);
        lesson.setTitle(lessonDto.getTitle());
        lesson.setDescription(lessonDto.getDescription());
        lesson.setOrder(lessonDto.getOrder());

        lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(String lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
