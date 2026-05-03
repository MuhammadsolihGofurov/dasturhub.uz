package dasturhub.uz.controller;

import org.springframework.ui.Model;
import dasturhub.uz.entity.Section;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sections")
public class SectionController {

    @PostMapping("/add/{courseId}")
    public String addSection(@PathVariable String courseId, @ModelAttribute Section section) {
        // Kursga yangi section qo'shish logikasi
        return "redirect:/courses/" + courseId;
    }

    @GetMapping("/edit/{id}")
    public String editSection(@PathVariable String id, Model model) {
        // Sectionni tahrirlash sahifasiga o'tish
        return "sections/edit";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSection(@PathVariable String id) {
        // Sectionni o'chirish logikasi
        return "redirect:/courses";
    }
}
