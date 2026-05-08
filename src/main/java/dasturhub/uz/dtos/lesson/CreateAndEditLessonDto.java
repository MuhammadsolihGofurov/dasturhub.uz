package dasturhub.uz.dtos.lesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAndEditLessonDto {
    private String sectionId;
    private String title;
    private String description;
    private Integer order;

}
