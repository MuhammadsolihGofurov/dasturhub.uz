package dasturhub.uz.services.section;

import dasturhub.uz.dtos.section.SectionCreateAndEditDto;
import dasturhub.uz.entity.Course;
import dasturhub.uz.entity.Section;
import dasturhub.uz.repository.CourseRepository;
import dasturhub.uz.repository.SectionRepository;
import dasturhub.uz.services.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ICourseService courseService;


    @Override
    public List<Section> getSectionByCourseIdAndOrderIncrease(String courseId) {
        return sectionRepository.findSectionsByCourse(courseId);
    }

    @Override
    public void addSection(SectionCreateAndEditDto sectionDto) {
        Course course = courseService.getCourseById(sectionDto.getCourseId());

        Section section = new Section();
        section.setTitle(sectionDto.getTitle());
        section.setDescription(sectionDto.getDescription());
        section.setOrder(sectionDto.getOrder());
        section.setCourse(course);

        sectionRepository.save(section);
    }

    @Override
    public Section getSectionById(String sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() -> new RuntimeException("section not found"));
    }

    @Override
    public void updateSection(String sectionId, SectionCreateAndEditDto sectionDto) {
        Section section = getSectionById(sectionId);

        section.setTitle(sectionDto.getTitle());
        section.setDescription(sectionDto.getDescription());
        section.setOrder(sectionDto.getOrder());
        sectionRepository.save(section);

    }

    @Override
    public void deleteSection(String sectionId) {
        sectionRepository.deleteById(sectionId);
    }

}
