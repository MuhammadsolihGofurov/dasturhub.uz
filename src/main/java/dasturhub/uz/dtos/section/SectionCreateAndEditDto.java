package dasturhub.uz.dtos.section;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionCreateAndEditDto {

    private String title;
    private String description;
    private Integer order;
    private String courseId;

}
