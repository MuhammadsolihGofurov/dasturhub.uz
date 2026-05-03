package dasturhub.uz.controller;

import org.springframework.ui.Model;
import dasturhub.uz.entity.Course;
import dasturhub.uz.entity.Page;
import dasturhub.uz.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private PageService pageService;

    @GetMapping
    public String getAllCourses(Model model) {
        Page homePage = pageService.getPage(3L);

        model.addAttribute("seo_title", homePage.getSeoTitle());
        model.addAttribute("seo_description", homePage.getSeoDescription());
        model.addAttribute("seo_keywords", homePage.getSeoKeywords());



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
