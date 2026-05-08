package dasturhub.uz.services.block;

import dasturhub.uz.dtos.block.CreateAndEditBlockDto;
import dasturhub.uz.entity.Block;

import java.util.List;

public interface IBlockService {
    List<Block> getAllByLessonIdAndOrder(String lessonId);

    void saveBlock(CreateAndEditBlockDto blockDto);

    Block getBlockById(String blockId);

    void updateBlockById(String blockId, CreateAndEditBlockDto blockDto);

    void deleteBlockById(String blockId);
}
