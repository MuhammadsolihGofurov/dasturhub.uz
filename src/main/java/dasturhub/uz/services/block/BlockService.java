package dasturhub.uz.services.block;

import dasturhub.uz.dtos.block.CreateAndEditBlockDto;
import dasturhub.uz.entity.Block;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.entity.enums.BlockType;
import dasturhub.uz.repository.BlockRepository;
import dasturhub.uz.services.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService implements IBlockService {

    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private LessonService lessonService;

    @Override
    public List<Block> getAllByLessonIdAndOrder(String lessonId) {
        return blockRepository.getAllByLessonIdAndOrder(lessonId);
    }

    @Override
    public void saveBlock(CreateAndEditBlockDto blockDto) {
        Lesson lesson = lessonService.getLessonById(blockDto.getLessonId());

        Block block = new Block();
        block.setLesson(lesson);
        block.setTitle(blockDto.getTitle());
        block.setContent(blockDto.getContent());
        block.setOrder(blockDto.getOrder());
        block.setBlockType(BlockType.valueOf(blockDto.getBlockType()));

        blockRepository.save(block);

    }

    @Override
    public Block getBlockById(String blockId) {
        return blockRepository.findById(blockId).orElseThrow(() -> new RuntimeException("Block not found"));
    }
}
