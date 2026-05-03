package dasturhub.uz.controller;

import org.springframework.ui.Model;
import dasturhub.uz.entity.Lesson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @GetMapping("/{id}")
    public String getLesson(@PathVariable String id, Model model) {
        // Dars mazmuni va uning bloklarini ko'rsatish
        return "lessons/view";
    }

    @PostMapping("/save/{sectionId}")
    public String saveLesson(@PathVariable String sectionId, @ModelAttribute Lesson lesson) {
        // Darsni sectionga biriktirib saqlash logikasi
        return "redirect:/sections/edit/" + sectionId;
    }
}
