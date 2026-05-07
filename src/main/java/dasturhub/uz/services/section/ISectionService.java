package dasturhub.uz.services.section;

import dasturhub.uz.dtos.section.SectionCreateAndEditDto;
import dasturhub.uz.entity.Section;

import java.util.List;

public interface ISectionService {

    List<Section> getSectionByCourseIdAndOrderIncrease(String courseId);

    void addSection(SectionCreateAndEditDto sectionDto);

    Section getSectionById(String sectionId);

    void updateSection(String sectionId, SectionCreateAndEditDto sectionDto);

    void deleteSection(String sectionId);
}
