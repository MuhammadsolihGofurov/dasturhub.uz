package dasturhub.uz.controller;

import ch.qos.logback.core.model.Model;
import dasturhub.uz.entity.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @GetMapping
    public String getAllCourses(Model model) {
        // Hamma kurslarni olish logikasi
        return "courses";
    }

    @GetMapping("/{id}")
    public String getCourseDetails(@PathVariable String id, Model model) {
        // Kurs tafsilotlari va uning sectionlarini olish logikasi
        return "courses/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Yangi kurs yaratish formasi
        return "courses/create";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        // Kursni saqlash logikasi
        return "redirect:/courses";
    }
}
