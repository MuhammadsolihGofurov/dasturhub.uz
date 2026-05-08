package dasturhub.uz.dtos.block;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAndEditBlockDto {

    private String title;
    private String content;
    private String blockType;
    private String lessonId;
    private Integer order;

}
