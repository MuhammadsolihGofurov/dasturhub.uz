package dasturhub.uz.controller;

import dasturhub.uz.entity.Block;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blocks")
public class BlockController {

    @PostMapping("/add-to-lesson/{lessonId}")
    public String addBlockToLesson(@PathVariable String lessonId, @ModelAttribute Block block) {
        // Darsga yangi kontent blokini qo'shish (Text, Video, Code)
        return "redirect:/lessons/" + lessonId;
    }

    @PostMapping("/update/{id}")
    public String updateBlock(@PathVariable String id, @ModelAttribute Block block) {
        // Mavjud blokni tahrirlash (content o'zgarganda)
        return "redirect:/lessons/view";
    }
}
