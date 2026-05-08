package dasturhub.uz.controller;

import dasturhub.uz.dtos.block.CreateAndEditBlockDto;
import dasturhub.uz.entity.Block;
import dasturhub.uz.entity.Lesson;
import dasturhub.uz.services.block.IBlockService;
import dasturhub.uz.services.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blocks")
public class BlockController {

    @Autowired
    private IBlockService blockService;
    @Autowired
    private LessonService lessonService;

    //  === for admin ===
    @GetMapping("/manage/{lessonId}")
    public String manageBlocks(@PathVariable("lessonId") String lessonId, Model model) {
        List<Block> blocks = blockService.getAllByLessonIdAndOrder(lessonId);
        Lesson lesson = lessonService.getLessonById(lessonId);

        model.addAttribute("blocks", blocks);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("sectionId", lesson.getSection().getId());
        model.addAttribute("seo_title", "Blokni boshqarish");

        return "/blocks/manage";
    }

    @GetMapping("/create/{lessonId}")
    public String createBlocks(@PathVariable("lessonId") String lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("seo_title", "Blokni boshqarish");

        return "/blocks/create";
    }

    @PostMapping("/save")
    public String saveBlock(@ModelAttribute CreateAndEditBlockDto blockDto) {
        blockService.saveBlock(blockDto);

        return "redirect:/blocks/manage/" + blockDto.getLessonId();
    }

    @GetMapping("/edit/{blockId}")
    public String editBlock(@PathVariable("blockId") String blockId, Model model) {
        Block block = blockService.getBlockById(blockId);

        model.addAttribute("blockId", blockId);
        model.addAttribute("block", block);
        model.addAttribute("lessonId", block.getLesson().getId());

        return "/blocks/edit";
    }

    @PostMapping("/update/{blockId}/{lessonId}")
    public  String updateBlock(@ModelAttribute CreateAndEditBlockDto blockDto, @PathVariable String blockId, @PathVariable String lessonId) {
        blockService.updateBlockById(blockId, blockDto);

        return "redirect:/blocks/manage/" + lessonId;
    }

    @DeleteMapping("/api/manage/{blockId}")
    @ResponseBody
    public void deleteBlock(@PathVariable("blockId") String blockId) {
        blockService.deleteBlockById(blockId);
    }

}
